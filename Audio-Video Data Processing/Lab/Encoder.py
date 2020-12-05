import math


def makeBlock(block):
    dimension = int(math.sqrt(len(block)))
    index = 0
    matrix = [[0 for x in range(dimension)] for y in range(dimension)]
    for i in range(dimension):
        for j in range(dimension):
            matrix[i][j] = block[index]
            index += 1
    return matrix


def alpha(val):
    if val == 0:
        return 1 / math.sqrt(2)
    else:
        return 1


def zigzag(matrix):
    solution = [[] for i in range(15)]

    for i in range(8):
        for j in range(8):
            sum = i + j
            if sum % 2 == 0:
                solution[sum].insert(0, matrix[i][j])
            else:
                solution[sum].append(matrix[i][j])

    arr = []
    for i in solution:
        for j in i:
            arr.append(j)

    return arr


def computeSize(value):
    if value == -1 or value == 1:
        return 1
    if value == -3 or value == -2 or value == 2 or value == 3:
        return 2
    if (-7 <= value <= -4) or (4 <= value <= 7):
        return 3
    if (-15 <= value <= -8) or (8 <= value <= 15):
        return 4
    if (-31 <= value <= -16) or (16 <= value <= 31):
        return 5
    if (-63 <= value <= -32) or (32 <= value <= 63):
        return 6
    if (-127 <= value <= -64) or (64 <= value <= 127):
        return 7
    if (-255 <= value <= -128) or (128 <= value <= 255):
        return 8
    if (-511 <= value <= -256) or (256 <= value <= 511):
        return 9
    if (-1023 <= value <= -512) or (512 <= value <= 1023):
        return 10


class Encoder:
    RGBpixels = []
    Ypixels = []
    Upixels = []
    Vpixels = []
    height = 0
    width = 0
    YList = []
    UList = []
    VList = []
    CbList = []
    CrList = []
    DCTsOutOfY = []
    DCTsOutOfCb = []
    DCTsOutOfCr = []
    arrayY = []
    arrayCb = []
    arrayCr = []
    outputArray = []

    def __init__(self, filename):
        self.readFile(filename)
        self.convertToYUV()
        self.YMatrix = self.createMatrixes(self.Ypixels)
        self.UMatrix = self.createMatrixes(self.Upixels)
        self.VMatrix = self.createMatrixes(self.Vpixels)
        self.createYBlocks()
        self.createUBlocks()
        self.createVBlocks()
        self.convertAllListsToBlocks()
        self.createCbBlocks()
        self.createCrBlocks()
        self.substract128()
        self.createForwardDCT()
        self.quantization()
        self.convertFrom8x8ToArray()
        self.makeOutputArray()
        self.writeToFile()

    def readFile(self, filename):
        with open(filename) as f:
            f.readline()
            line = f.readline()
            while line[0] == "#":
                line = f.readline().split(" ")

            self.height = int(line[1])
            self.width = int(line[0].strip(" "))
            f.readline()
            line = f.readline()
            while line:
                self.RGBpixels.append(line.strip('\n'))
                line = f.readline()

    def convertToYUV(self):
        for i in range(0, len(self.RGBpixels) - 1, 3):
            R = float(self.RGBpixels[i])
            G = float(self.RGBpixels[i + 1])
            B = float(self.RGBpixels[i + 2])
            Y = 0.299 * R + 0.587 * G + 0.114 * B
            U = -0.147 * R - 0.289 * G + 0.436 * B
            V = 0.615 * R - 0.515 * G - 0.100 * B
            self.Ypixels.append(Y)
            self.Upixels.append(U)
            self.Vpixels.append(V)
        print("Converted to YUV\n")

    def createMatrixes(self, list):
        matrix = [[0 for x in range(self.width)] for y in range(self.height)]
        index = 0
        for i in range(self.height):
            for j in range(self.width):
                matrix[i][j] = list[index]
                index += 1
        return matrix

    def createYBlocks(self):
        for i in range(0, self.height, 8):
            for j in range(0, self.width, 8):
                list = []
                for m in range(i, i + 8):
                    for n in range(j, j + 8):
                        list.append(self.YMatrix[m][n])
                self.YList.append([list, "Y", i, j])

    def createUBlocks(self):
        for i in range(0, self.height, 8):
            for j in range(0, self.width, 8):
                list = []
                for m in range(i, i + 8, 2):
                    for n in range(j, j + 8, 2):
                        list.append((self.UMatrix[m][n] + self.UMatrix[m][n + 1] + self.UMatrix[m + 1][n] +
                                     self.UMatrix[m + 1][n + 1]) / 4)
                self.UList.append([list, "U", i, j])

    def createVBlocks(self):
        for i in range(0, self.height, 8):
            for j in range(0, self.width, 8):
                list = []
                for m in range(i, i + 8, 2):
                    for n in range(j, j + 8, 2):
                        list.append((self.VMatrix[m][n] + self.VMatrix[m][n + 1] + self.VMatrix[m + 1][n] +
                                     self.VMatrix[m + 1][n + 1]) / 4)
                self.VList.append([list, "V", i, j])
        print("Blocks created\n")

    def convertAllListsToBlocks(self):
        for i in range(len(self.YList)):
            self.YList[i][0] = makeBlock(self.YList[i][0])
            self.UList[i][0] = makeBlock(self.UList[i][0])
            self.VList[i][0] = makeBlock(self.VList[i][0])

    def createCbBlocks(self):
        for index in range(len(self.UList)):
            matrix = [[0 for x in range(8)] for y in range(8)]
            for i in range(4):
                for j in range(4):
                    matrix[i * 2][j * 2] = self.UList[index][0][i][j]
                    matrix[i * 2][j * 2 + 1] = self.UList[index][0][i][j]
                    matrix[i * 2 + 1][j * 2] = self.UList[index][0][i][j]
                    matrix[i * 2 + 1][j * 2 + 1] = self.UList[index][0][i][j]
            self.CbList.append([matrix, "U", self.UList[index][2], self.UList[index][3]])

    def createCrBlocks(self):
        for index in range(len(self.VList)):
            matrix = [[0 for x in range(8)] for y in range(8)]
            for i in range(4):
                for j in range(4):
                    matrix[i * 2][j * 2] = self.VList[index][0][i][j]
                    matrix[i * 2][j * 2 + 1] = self.VList[index][0][i][j]
                    matrix[i * 2 + 1][j * 2] = self.VList[index][0][i][j]
                    matrix[i * 2 + 1][j * 2 + 1] = self.VList[index][0][i][j]
            self.CrList.append([matrix, "V", self.VList[index][2], self.VList[index][3]])

        print("Y/Cb/Cr blocks created\n")

    def substract128(self):
        for index in range(len(self.YList)):
            for i in range(8):
                for j in range(8):
                    self.YList[index][0][i][j] -= 128
                    self.CbList[index][0][i][j] -= 128
                    self.CrList[index][0][i][j] -= 128

    def createForwardDCT(self):
        for index in range(len(self.YList)):
            matrixY = [[0 for x in range(8)] for y in range(8)]
            matrixCb = [[0 for x in range(8)] for y in range(8)]
            matrixCr = [[0 for x in range(8)] for y in range(8)]
            for u in range(8):
                for v in range(8):
                    valY = 0
                    valCb = 0
                    valCr = 0
                    for x in range(8):
                        for y in range(8):
                            valY += self.YList[index][0][x][y] * math.cos(((2 * x + 1) * u * math.pi) / 16) * math.cos(
                                ((2 * y + 1) * v * math.pi) / 16)
                            valCb += self.CbList[index][0][x][y] * math.cos(
                                ((2 * x + 1) * u * math.pi) / 16) * math.cos(
                                ((2 * y + 1) * v * math.pi) / 16)
                            valCr += self.CrList[index][0][x][y] * math.cos(
                                ((2 * x + 1) * u * math.pi) / 16) * math.cos(
                                ((2 * y + 1) * v * math.pi) / 16)
                    matrixY[u][v] = (1 / 4) * alpha(u) * alpha(v) * valY
                    matrixCb[u][v] = (1 / 4) * alpha(u) * alpha(v) * valCb
                    matrixCr[u][v] = (1 / 4) * alpha(u) * alpha(v) * valCr
            self.DCTsOutOfY.append(matrixY)
            self.DCTsOutOfCb.append(matrixCb)
            self.DCTsOutOfCr.append(matrixCr)
        print("DCT Done\n")

    def quantization(self):
        Q = [
            [6, 4, 4, 6, 10, 16, 20, 24],
            [5, 5, 6, 8, 10, 23, 24, 22],
            [6, 5, 6, 10, 16, 23, 28, 22],
            [6, 7, 9, 12, 20, 35, 32, 25],
            [7, 9, 15, 22, 27, 44, 41, 31],
            [10, 14, 22, 26, 32, 42, 45, 37],
            [20, 26, 31, 35, 41, 48, 48, 40],
            [29, 37, 38, 39, 45, 40, 41, 40]
        ]

        for index in range(len(self.DCTsOutOfY)):
            for i in range(8):
                for j in range(8):
                    self.DCTsOutOfY[index][i][j] = math.ceil(self.DCTsOutOfY[index][i][j] / Q[i][j])
                    self.DCTsOutOfCb[index][i][j] = math.ceil(self.DCTsOutOfCb[index][i][j] / Q[i][j])
                    self.DCTsOutOfCr[index][i][j] = math.ceil(self.DCTsOutOfCr[index][i][j] / Q[i][j])

        print("Quantization Done\n")

    def convertFrom8x8ToArray(self):
        for i in range(len(self.DCTsOutOfY)):
            self.arrayY.append(zigzag(self.DCTsOutOfY[i]))
            self.arrayCb.append(zigzag(self.DCTsOutOfCb[i]))
            self.arrayCr.append(zigzag(self.DCTsOutOfCr[i]))
        print("8x8 blocks converted to arrays\n")

    def makeOutputArray(self):
        for i in range(len(self.arrayY)):
            arr = []
            countZero = 0
            for j in range(64):
                a = []
                if j == 0:
                    a = [computeSize(self.arrayY[i][j]), self.arrayY[i][j]]
                elif j == 63:
                    if self.arrayY[i][j] == 0:
                        a = [0, 0]
                    else:
                        a = [countZero, computeSize(self.arrayY[i][j]), self.arrayY[i][j]]
                else:
                    if self.arrayY[i][j] != 0:
                        a = [countZero, computeSize(self.arrayY[i][j]), self.arrayY[i][j]]
                        countZero = 0
                    else:
                        countZero += 1
                if a:
                    arr.append(a)
            self.outputArray.append(arr)

            arr = []
            countZero = 0
            for j in range(64):
                a = []
                if j == 0:
                    a = [computeSize(self.arrayCb[i][j]), self.arrayCb[i][j]]
                elif j == 63:
                    if self.arrayCb[i][j] == 0:
                        a = [0, 0]
                    else:
                        a = [countZero, computeSize(self.arrayCb[i][j]), self.arrayCb[i][j]]
                else:
                    if self.arrayCb[i][j] != 0:
                        a = [countZero, computeSize(self.arrayCb[i][j]), self.arrayCb[i][j]]
                        countZero = 0
                    else:
                        countZero += 1
                if a:
                    arr.append(a)
            self.outputArray.append(arr)

            arr = []
            countZero = 0
            for j in range(64):
                a = []
                if j == 0:
                    a = [computeSize(self.arrayCr[i][j]), self.arrayCr[i][j]]
                elif j == 63:
                    if self.arrayCr[i][j] == 0:
                        a = [0, 0]
                    else:
                        a = [countZero, computeSize(self.arrayCr[i][j]), self.arrayCr[i][j]]
                else:
                    if self.arrayCr[i][j] != 0:
                        a = [countZero, computeSize(self.arrayCr[i][j]), self.arrayCr[i][j]]
                        countZero = 0
                    else:
                        countZero += 1
                if a:
                    arr.append(a)
            self.outputArray.append(arr)
        print("OutputArray done!\n")

    def writeToFile(self):
        f = open("data/encodedList.txt", "w")
        for i in self.outputArray:
            f.write(str(i) + '\n')
        f.close()


if __name__ == '__main__':
    pass
