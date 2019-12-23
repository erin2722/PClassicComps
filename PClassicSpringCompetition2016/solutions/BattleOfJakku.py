def true_operations(arraylength, array1, array2, operator):
    outputarray = []
    #STUBIFY
    for x in range(0, arraylength):
        if operator == "&":
            outputarray.append(int(array1[x]) & int(array2[x]))
        if operator == "|":
            outputarray.append(int(array1[x]) | int(array2[x]))
        if operator == "^":
            outputarray.append(int(array1[x]) ^ int(array2[x]))
    #ENDSTUBIFY

    return outputarray

def parse_file_and_call_function():
    with open("BattleOfJakkuIN.txt", "r") as f:
        while True:
            arraylength = f.readline()
            if arraylength == '':
                break
            array1 = f.readline().split(' ')
            array2 = f.readline().split(' ')
            operator = f.readline().strip()
            arraylength = int(arraylength)
            x = true_operations(arraylength, array1[0:arraylength], array2[0:arraylength], operator)

            print " ".join([str(y) for y in x])



if __name__ == "__main__":
    parse_file_and_call_function()
