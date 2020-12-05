import math


def alpha(val):
    if val == 0:
        return 1 / math.sqrt(2)
    else:
        return 1


def makeList(block):
    list = [0] * 64
    position = 0
    for i in range(8):
        for j in range(8):
            list[position] = block[i][j]
            position += 1
    return list


def makeList4x4(block):
    list = [0] * 16
    pos = 0
    for i in range(4):
        for j in range(4):
            list[pos] = block[i][j]
            pos += 1
    return list


def zigzagBlock(arr):
    matrix = [[0 for _ in range(8)] for _ in range(8)]
    matrix[0][0] = arr[0]
    matrix[0][1] = arr[1]
    matrix[1][0] = arr[2]
    matrix[2][0] = arr[3]
    matrix[1][1] = arr[4]
    matrix[0][2] = arr[5]
    matrix[0][3] = arr[6]
    matrix[1][2] = arr[7]
    matrix[2][1] = arr[8]
    matrix[3][0] = arr[9]
    matrix[4][0] = arr[10]
    matrix[3][1] = arr[11]
    matrix[2][2] = arr[12]
    matrix[1][3] = arr[13]
    matrix[0][4] = arr[14]
    matrix[0][5] = arr[15]
    matrix[1][4] = arr[16]
    matrix[2][3] = arr[17]
    matrix[3][2] = arr[18]
    matrix[4][1] = arr[19]
    matrix[5][0] = arr[20]
    matrix[6][0] = arr[21]
    matrix[5][1] = arr[22]
    matrix[4][2] = arr[23]
    matrix[3][3] = arr[24]
    matrix[2][4] = arr[25]
    matrix[1][5] = arr[26]
    matrix[0][6] = arr[27]
    matrix[0][7] = arr[28]
    matrix[1][6] = arr[29]
    matrix[2][5] = arr[30]
    matrix[3][4] = arr[31]
    matrix[4][3] = arr[32]
    matrix[5][2] = arr[33]
    matrix[6][1] = arr[34]
    matrix[7][0] = arr[35]
    matrix[7][1] = arr[36]
    matrix[6][2] = arr[37]
    matrix[5][3] = arr[38]
    matrix[4][4] = arr[39]
    matrix[3][5] = arr[40]
    matrix[2][6] = arr[41]
    matrix[1][7] = arr[42]
    matrix[2][7] = arr[43]
    matrix[3][6] = arr[44]
    matrix[4][5] = arr[45]
    matrix[5][4] = arr[46]
    matrix[6][3] = arr[47]
    matrix[7][2] = arr[48]
    matrix[7][3] = arr[49]
    matrix[6][4] = arr[50]
    matrix[5][5] = arr[51]
    matrix[6][4] = arr[52]
    matrix[7][3] = arr[53]
    matrix[4][7] = arr[54]
    matrix[5][6] = arr[55]
    matrix[6][5] = arr[56]
    matrix[7][4] = arr[57]
    matrix[7][5] = arr[58]
    matrix[6][6] = arr[59]
    matrix[5][7] = arr[60]
    matrix[6][7] = arr[61]
    matrix[7][6] = arr[62]
    matrix[7][7] = arr[63]
    return matrix


class Decoder:
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
    array = []
    height = 600
    width = 800
    YPixels = []
    UPixels = []
    VPixels = []
    RGBPixels = []
    filename = ""

    def __init__(self, array, filename):
        self.array = array
        self.filename = filename
        self.YMatrix = [[0 for x in range(self.width)] for y in range(self.height)]
        self.UMatrix = [[0 for x in range(self.width)] for y in range(self.height)]
        self.VMatrix = [[0 for x in range(self.width)] for y in range(self.height)]
        self.constructArrays()
        self.convertFromArraysTo8x8Blocks()
        self.DeQuantization()
        self.inverseDCT()
        self.add128()
        self.convertAllBlocksToLists()
        self.createYMatrix()
        self.createUMatrix()
        self.createVMatrix()
        self.createYUVpixels()
        self.createRGBpixels()
        self.createFile()

    def constructArrays(self):
        for i in range(0, len(self.array), 3):
            arr = []
            count = 0
            for j in range(len(self.array[i])):
                if j == 0:
                    arr.append(self.array[i][j][1])
                    count += 1
                elif self.array[i][j] == [0, 0]:
                    while count != 64:
                        arr.append(0)
                        count += 1
                else:
                    for c in range(self.array[i][j][0]):
                        arr.append(0)
                        count += 1
                    arr.append(self.array[i][j][2])
                    count += 1
            self.arrayY.append(arr)

            arr = []
            count = 0
            for j in range(len(self.array[i + 1])):
                if j == 0:
                    arr.append(self.array[i + 1][j][1])
                    count += 1
                elif self.array[i + 1][j] == [0, 0]:
                    while count != 64:
                        arr.append(0)
                        count += 1
                else:
                    for c in range(self.array[i + 1][j][0]):
                        arr.append(0)
                        count += 1
                    arr.append(self.array[i + 1][j][2])
                    count += 1
            self.arrayCb.append(arr)

            arr = []
            count = 0
            for j in range(len(self.array[i + 2])):
                if j == 0:
                    arr.append(self.array[i + 2][j][1])
                    count += 1
                elif self.array[i + 2][j] == [0, 0]:
                    while count != 64:
                        arr.append(0)
                        count += 1
                else:
                    for c in range(self.array[i + 2][j][0]):
                        arr.append(0)
                        count += 1
                    arr.append(self.array[i + 2][j][2])
                    count += 1
            self.arrayCr.append(arr)
        print("Arrays constructed ! \n")

    def convertFromArraysTo8x8Blocks(self):
        for i in range(len(self.arrayY)):
            self.DCTsOutOfY.append(zigzagBlock(self.arrayY[i]))
            self.DCTsOutOfCb.append(zigzagBlock(self.arrayCb[i]))
            self.DCTsOutOfCr.append(zigzagBlock(self.arrayCr[i]))
        print("Converted to Blocks!")

    def DeQuantization(self):
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
                    self.DCTsOutOfY[index][i][j] = self.DCTsOutOfY[index][i][j] * Q[i][j]
                    self.DCTsOutOfCb[index][i][j] = self.DCTsOutOfCb[index][i][j] * Q[i][j]
                    self.DCTsOutOfCr[index][i][j] = self.DCTsOutOfCr[index][i][j] * Q[i][j]

        print("DeQuantization Done \n")

    def inverseDCT(self):
        for index in range(len(self.DCTsOutOfY)):
            matrixY = [[0 for x in range(8)] for y in range(8)]
            matrixCb = [[0 for x in range(8)] for y in range(8)]
            matrixCr = [[0 for x in range(8)] for y in range(8)]
            for x in range(8):
                for y in range(8):
                    valY = 0
                    valCb = 0
                    valCr = 0
                    for u in range(8):
                        for v in range(8):
                            valY += alpha(u) * alpha(v) * self.DCTsOutOfY[index][u][v] * math.cos(
                                ((2 * x + 1) * u * math.pi) / 16) * math.cos(((2 * y + 1) * v * math.pi) / 16)
                            valCb += alpha(u) * alpha(v) * self.DCTsOutOfCb[index][u][v] * math.cos(
                                ((2 * x + 1) * u * math.pi) / 16) * math.cos(((2 * y + 1) * v * math.pi) / 16)
                            valCr += alpha(u) * alpha(v) * self.DCTsOutOfCr[index][u][v] * math.cos(
                                ((2 * x + 1) * u * math.pi) / 16) * math.cos(((2 * y + 1) * v * math.pi) / 16)
                    matrixY[x][y] = (1 / 4) * valY
                    matrixCb[x][y] = (1 / 4) * valCb
                    matrixCr[x][y] = (1 / 4) * valCr
            self.YList.append(matrixY)
            self.CbList.append(matrixCb)
            self.CrList.append(matrixCr)
        print("Inverse DCT Done \n")

    def add128(self):
        for index in range(len(self.YList)):
            for i in range(8):
                for j in range(8):
                    self.YList[index][i][j] += 128
                    self.CbList[index][i][j] += 128
                    self.CrList[index][i][j] += 128

    def convertAllBlocksToLists(self):
        for i in range(len(self.YList)):
            self.YList[i] = makeList(self.YList[i])
            self.UList.append(makeList(self.CbList[i]))
            self.VList.append(makeList(self.CrList[i]))

    def createYMatrix(self):
        index = 0
        for i in range(0, self.height, 8):
            for j in range(0, self.width, 8):
                position = 0
                for m in range(i, i + 8):
                    for n in range(j, j + 8):
                        self.YMatrix[m][n] = self.YList[index][position]
                        position += 1
                index += 1

    def createUMatrix(self):
        index = 0
        for i in range(0, self.height, 8):
            for j in range(0, self.width, 8):
                position = 0
                for m in range(i, i + 8):
                    for n in range(j, j + 8):
                        self.UMatrix[m][n] = self.UList[index][position]
                        position += 1
                index += 1

    def createVMatrix(self):
        index = 0
        for i in range(0, self.height, 8):
            for j in range(0, self.width, 8):
                position = 0
                for m in range(i, i + 8):
                    for n in range(j, j + 8):
                        self.VMatrix[m][n] = self.VList[index][position]
                        position += 1
                index += 1
        print("Matrixes created \n")

    def createYUVpixels(self):
        for i in range(self.height):
            for j in range(self.width):
                self.YPixels.append(self.YMatrix[i][j])
                self.UPixels.append(self.UMatrix[i][j])
                self.VPixels.append(self.VMatrix[i][j])

    def createRGBpixels(self):
        for i in range(len(self.YPixels)):
            Y = self.YPixels[i]
            U = self.UPixels[i]
            V = self.VPixels[i]
            R = Y + 1.140 * V
            G = Y - 0.395 * U - 0.581 * V
            B = Y + 2.032 * U
            self.RGBPixels.append(R)
            self.RGBPixels.append(G)
            self.RGBPixels.append(B)

    def createFile(self):
        f = open(self.filename, "w")
        f.write("P3")
        f.write('\n')
        f.write("# CREATOR: GIMP PNM Filter Version 1.1")
        f.write('\n')
        f.write("800 600")
        f.write('\n')
        f.write("255")
        f.write('\n')
        for i in range(len(self.RGBPixels)):
            if self.RGBPixels[i] > 255:
                self.RGBPixels[i] = 255
            if self.RGBPixels[i] < 0:
                self.RGBPixels[i] = 0
            f.write(str(int(self.RGBPixels[i])))
            f.write('\n')


if __name__ == '__main__':
    pass
