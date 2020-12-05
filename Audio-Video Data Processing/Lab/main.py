import time

from Decoder import Decoder
from Encoder import Encoder

if __name__ == '__main__':
    start = time.time()
    enc = Encoder("data/nt-P3.ppm")
    dec = Decoder(enc.outputArray, "data/resultFile.ppm")
    print(time.time() - start)
