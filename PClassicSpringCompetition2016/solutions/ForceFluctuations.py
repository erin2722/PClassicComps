def recurse(arr, l, r):
    if l >= r:
        return
    m = (l + r) // 2
    recurse(arr, l, m)
    recurse(arr, m+1, r)
    lp = l
    rp = m + 1
    rc = 0
    lc = m - l + 1
    tArr = []
    while (lp <= m or rp <= r):
        if (rp == r+1) or (lp <= m and rp <= r and arr[lp][0] < arr[rp][0]):
            tArr.append((arr[lp][0], arr[lp][1] + rc, arr[lp][2]))
            lp += 1
            lc -= 1
        elif (lp == m+1) or (lp <= m and rp <= r and arr[lp][0] > arr[rp][0]):
            tArr.append((arr[rp][0], arr[rp][1], arr[rp][2] + lc))
            rp += 1
            rc += 1

    for i, x in enumerate(tArr):
        arr[l + i] = x

    return


def count_triples(measurements):
    N = len(measurements)
    arr = [(x, 0, 0) for x in measurements]
    recurse(arr, int(0), int(N-1))
    ret = 0
    for (x, f, b) in arr:
        ret += f * b
    return ret


if __name__ == "__main__":
    with open("ForceFluctuationsIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == "":
                break
            N = int(s)
            s = f.readline()
            data = s.split(" ")
            measurements = [int(x) for x in data]
            print(count_triples(measurements))
