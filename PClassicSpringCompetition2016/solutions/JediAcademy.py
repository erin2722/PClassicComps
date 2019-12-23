from __future__ import division

def best_grouping(scores, G):
    INFINITY = 1000000000
    scores = sorted(scores)
    N = len(scores)
    dp = [[INFINITY for i in range(G)] for j in range(N)]
    sums = []
    sqsums = []
    for i in range(N):
        if i == 0:
            sums.append(scores[i])
            sqsums.append(scores[i] * scores[i])
        else:
            sums.append(sums[i - 1] + scores[i])
            sqsums.append(sqsums[i - 1] + scores[i] * scores[i])
        dp[i][0] = sqsums[i] - (sums[i] * sums[i]) / (i + 1)

    def sqs(x):
        if x < 0:
            return 0
        else:
            return sqsums[x]
    def s(x):
        if x < 0:
            return 0
        else:
            return sums[x]

    prec = [[sqs(i) - sqs(k) - (s(i) - s(k)) * (s(i) - s(k)) / (i - k) for k in range(i)] for i in range(N)]

    for i in range(N):
        for j in range(1, G):
            for k in range(i):
                dp[i][j] = min(dp[i][j], dp[k][j - 1] + prec[i][k])

    return int(dp[N - 1][G - 1])

if __name__ == "__main__":
    with open("JediAcademyIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == "":
                break
            data = s.split("--")
            scores = [int(x) for x in data[0].split(",")]
            G = int(data[1])
            print(best_grouping(scores, G))
