def closest_vowel(s):
    vowels = ["a", "e", "i", "o", "u"]
    word = s
    i = 0
    j = 0
    f = 0
    e = len(word) - 1
    for i in range(0, len(word)):
        if word[f] is vowels[0] or word[f] is vowels[1] or word[f] is vowels[2] or word[f] is vowels[3] or word[f] is vowels[4]:
            return word[f]
            break
        elif word[e] is vowels[0] or word[e] is vowels[1] or word[e] is vowels[2] or word[e] is vowels[3] or word[e] is vowels[4]:
            return word[e]
            break
        f += 1
        e -= 1
        if f == e:
            break
            
    

"""FILE PARSING - DO NOT MODIFY"""


def parse_file_and_call_function():
    with open("CipherCrackingIN.txt", "r") as f:
        for line in f:
            print(closest_vowel(line.rstrip()))

if __name__ == "__main__":
    parse_file_and_call_function()
