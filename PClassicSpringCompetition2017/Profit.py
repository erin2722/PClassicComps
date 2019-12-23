def max_profit(matrix):
    #matrix=[[50,200,700,13],[20,1000,0,0],[0,0,0,0],[50,0,0,10],[23,15,0,0],[0,0,13,0],[0,13,35,68]]
    heistSum=0
    greatest_sum=0
    i=0
    j=0
    heist_day = ""
    for i in range(0, 7):
        for j in range(0, len(matrix[i])):
            heistSum += matrix[i][j]
        if heistSum>greatest_sum:
            greatest_sum=heistSum
            day=i
        heistSum=0
        
    if day==0:
        heist_day = "Monday"
    elif day ==1:
        heist_day = "Tuesday"
    elif day == 2:
        heist_day = "Wednesday"
    elif day == 3:
        heist_day = "Thursday"
    elif day == 4:
        heist_day = "Friday"
    elif day == 5:
        heist_day = "Saturday"
    elif day == 6:
        heist_day = "Sunday"
    return heist_day


"""FILE PARSING - DO NOT MODIFY"""


def parse_file_and_call_function():
    with open("MakingProfitIN.txt", "r") as f:
        while True:
            n = f.readline()
            if n == "":
                break
            m = f.readline()
            arr = []
            for x in range(0, 7):
                s = f.readline()
                s = s.strip()
                sArr = s.split(" ")
                input = []
                for y in range(0, len(sArr)):
                    input.append(int(sArr[y]))
                arr.append(input)
            print max_profit(arr)


if __name__ == "__main__":
    parse_file_and_call_function()
