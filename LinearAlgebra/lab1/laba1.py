import math
def sum(m1, m2):
    w1 = len(list(m1))
    h1 = len(list(list(m1)[0]))
    w2 = len(list(m2))
    h2 = len(list(list(m2)[0]))
    if w1 != w2 or h1 != h2:
        return 0
    m3 = [[0 for x in range(h1)] for y in range(w1)]
    for i in range(w1):
        for j in range(h1):
            m3[i][j] = float(int(m1[i][j]) + float(m2[i][j]))
    return m3


def sub(m1, m2):
    w1 = len(list(m1))
    h1 = len(list(list(m1)[0]))
    w2 = len(list(m2))
    h2 = len(list(list(m2)[0]))
    if w1 != w2 or h1 != h2:
        return 0
    m3 = [[0 for x in range(h1)] for y in range(w1)]
    for i in range(w1):
        for j in range(h1):
            m3[i][j] = int(int(m1[i][j]) - int(m2[i][j]))
    return m3


def mult_a(m1, alpha):
    w1 = len(list(m1))
    h1 = len(list(list(m1)[0]))
    m3 = [[0 for x in range(h1)] for y in range(w1)]
    for i in range(w1):
        for j in range(h1):
            m3[i][j] = float(float(m1[i][j]) * alpha)
    return m3


def transpone(m1):
    w1 = len(list(m1))
    h1 = len(list(list(m1)[0]))
    m3 = [[0 for x in range(w1)] for y in range(h1)]
    for i in range(h1):
        for j in range(w1):
            m3[i][j] = m1[j][i]
    return m3


def mult(m1, m2):
    w1 = len(list(m1))
    h1 = len(list(list(m1)[0]))
    w2 = len(list(m2))
    h2 = len(list(list(m2)[0]))
    m3 = [[0 for j in range(h2)] for i in range(w1)]
    if h1 == w2:
        for i in range(w1):
            for j in range(h1):
                for k in range(h2):
                    m3[i][j] += float(m1[i][k]) * float(m2[k][j])
        return m3
    return 0


data = open("input.txt", "r").read().split()
alpha = float(data[0])
beta = float(data[1])
wA = int(data[2])
hA = int(data[3])
mA = [[0 for i in range(hA)] for j in range(wA)]
for i in range(wA):
    for j in range(hA):
        mA[i][j] = data[hA * i + j + 4]
pos = (wA * hA + 4)
wB = int(data[pos])
hB = int(data[pos + 1])
mB = [[0 for i in range(hB)] for j in range(wB)]
pos += 2
for i in range(wB):
    for j in range(hB):
        mB[i][j] = data[hB * i + j + pos]

pos = pos + (wB * hB)
wC = int(data[pos])
hC = int(data[pos + 1])
mC = [[0 for i in range(hC)] for j in range(wC)]
pos += 2
for i in range(wC):
    for j in range(hC):
        mC[i][j] = data[hC * i + j + pos]
pos += wC * hC

wD = int(data[pos])
hD = int(data[pos + 1])
mD = [[0 for i in range(hD)] for j in range(wD)]
pos += 2
for i in range(wD):
    for j in range(hD):
        mD[i][j] = data[hD * i + j + pos]
pos += wD * wD
wF = int(data[pos])
hF = int(data[pos + 1])
mF = [[0 for i in range(hF)] for j in range(wF)]
pos += 2
for i in range(wF):
    for j in range(hF):
        mF[i][j] = data[hF * i + j + pos]

result = sum(mult_a(mA, alpha), mult_a((transpone(mB)), beta))
f2 = open("output.txt", "w")
if result == 0:
    f2.write("0")
    exit(0)
result = transpone(result)
result = mult(mC, result)
if result == 0:
    f2.write("0")
    exit(0)
result = mult(result, mD)
if result == 0:
    f2.write("0")
    exit(0)
result = sub(result, mF)
if result == 0:
    f2.write("0")
    exit(0)
f2.write("1\n")
w1 = len(list(result))
h1 = len(list(list(result)[0]))
f2.write(str(w1) + " " + str(h1)+"\n")
for i in range(w1):
    for j in range(h1):
        f2.write(str(result[i][j]))
        if j < h1 - 1:
            f2.write(" ")
    if i < w1 - 1:
        f2.write("\n")
f2.close()
