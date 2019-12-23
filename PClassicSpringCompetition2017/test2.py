n=4
c=[1, 2, 3]
num=0
for i in range(0, n):
    for j in range(0, len(c)):
        if i*(c[j])==n:
            num+=1
        for m in range(1, len(c)):
            
print num