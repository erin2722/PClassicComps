def closest_vowel(str):
    """

    :param str: string containing at least one vowel
    :return: vowel closest to head or tail of string
    """

    vowels = {'a', 'e', 'i', 'o', 'u'}
    test = "aeiou"
    min_dist = len(str);
    min_idx = -1;
    for i in xrange(len(str)):
        if str[i] in vowels:
            dist = min(i+1, len(str) - i);
            if dist < min_dist:
                min_dist = dist;
                min_idx = i;
    if min_idx < 0:
        return "Error";
    return str[min_idx]


"""FILE PARSING - DO NOT MODIFY"""


def parse_file_and_call_function():
    with open("CipherCrackingIN.txt", "r") as f:
        for line in f:
            print closest_vowel(line.rstrip())

if __name__ == "__main__":
    parse_file_and_call_function()
