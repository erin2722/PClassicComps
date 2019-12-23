#this program didnt work because it failed edge cases
import math

def different_numbers(numbers, n):
    i = 0
    j = 0
    m = 0
    for j in range(0, len(numbers)):
        for i in range(0, j):
            if abs(numbers[i] - numbers[j]) >= n:
                m += 1
            
    return m 

def parse_file_and_call_function():
    with open("GalacticCivilWarIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == '':
                break
            data = s.split(' ')
            numbers = f.readline().split(' ')
            n = data[1]
            x = different_numbers([int(y) for y in numbers], n)

            print (x)

if __name__ == "__main__":
    parse_file_and_call_function()
