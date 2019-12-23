import math

def different_numbers(numbers, n):
    x = 0
    #STUBIFY

    for i in xrange(len(numbers)):
        for j in xrange(i+1, len(numbers)):
            if abs(numbers[i] - numbers[j]) >= n:
                x += 1
    #ENDSTUBIFY
    return x


def parse_file_and_call_function():
    with open("GalacticCivilWarIN.txt", "r") as f:
        while True:
            data = f.readline().split(' ')
            next_line = f.readline()
            if not next_line:
                break
            numbers_in_strings = next_line.split(' ')
            numbers = [int(s) for s in numbers_in_strings]
            n = int(data[1])
            x = different_numbers(numbers, n)

            print x

if __name__ == "__main__":
    parse_file_and_call_function()
