n=3
lock=[[1, 2, 3], [8, 9, 4], [7, 6, 5]]
y=0
z=n

for j in range(y, z):
        print lock[0][j]

for i in range(y+1, z):
        print lock[i][z-1]
        
for j in range(y+1, z-1):
        line=str(lock[z-1][j])
        realline=line[::-1]
        print realline

for i in range(y+1, z):
        line2=lock[i][y]
        y-=1
        print line2

                