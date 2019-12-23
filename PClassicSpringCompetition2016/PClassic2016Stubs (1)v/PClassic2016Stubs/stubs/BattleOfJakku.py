#Fill out the body of this method
def true_operations(arraylength, array1, array2, operator):
    outputarray = []
    i = 0
    for i in range(0, int(arraylength)):
        if operator is "&":
            if array1[i] is "1" and array2[i] is "1":
                 outputarray.append("1")
            else:
                outputarray.append("0")
        if operator is "^":
            if array1[i] is "1" and array2[i] is "1":
                outputarray.append("0")
            else:
                outputarray.append("1")
        if operator is "|":
            if array1[i] is "1" or array2[i] is "1":
                outputarray.append("1")
            else:
                outputarray.append("0")
        break
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
