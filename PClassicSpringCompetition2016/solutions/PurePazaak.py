def score(cards):
    """

    :param cards: list of cards
    :return: score received
    """
    N = len(cards)
    T = [[0 for x in xrange(N)] for y in xrange(N)]

    for i in xrange(N - 1):
        T[i][i + 1] = max(cards[i], cards[i + 1])

    for k in xrange(3, N, 2):
        i = 0
        while i + k < len(cards):
            T[i][i + k] = max(
                min(T[i + 2][i + k], T[i + 1][i + k - 1]) + cards[i],
                min(T[i][i + k - 2], T[i + 1][i + k - 1]) + cards[i + k],
            )
            i += 1

    return T[0][N - 1]


"""FILE PARSING - DO NOT MODIFY"""


def parse_file_and_call_function():
    with open("PurePazaakIN.txt", "r") as f:
        for line in f:
            data = line.split(' ')
            cards = [int(x) for x in data[1:]]
            print score(cards)

if __name__ == "__main__":
    parse_file_and_call_function()
