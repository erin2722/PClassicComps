import math

def can_profit(prices):
    return False

if __name__ == "__main__":
    with open("BlackMarketIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == "":
                break
            rows = s.split(",")
            prices = []
            for row in rows:
                prices.append([float(x) for x in row.split(" ")])
            print("true" if can_profit(prices) else "false")
