# Lofi
Lofi comes up with an approach that aims at a power-efficient wifi-based protocol to estimate the location. The calculation is made based on the comparison between observed Received Signal Strength (RSS) values and the “Fingerprint” data in a pre-built radio map that has been obtained through specialized infrastructure deployment.

# Algorithm used 

Estimate the location Input: ﬁngerprint data fpunknown <br>
Output: estimated location l  <br>
1: score ← 0, scoreMAX ← 0  <br>
2: valueMAP ← {}  <br>
3:
4: for each possible fplx in the radio map do  <br>
5:     for each KEYunknown in fpunknown do  <br>
6:           if KEYunknown is found in fplx then  <br>
7:                valueMAP ← VALUE vector where its KEY = KEYunknown  <br>
8:                for each BSSID in KEYunknown do  <br>
9:                if BSSID is found in valueMAP then  <br>
10:                score ← score + 1  <br>
11:               end if  <br>
12:              end for  <br>
13:           end if  <br>
14:      end for  <br>
15:      if score > scoreMAX then  <br>
16:           scoreMAX ← score  <br>
17:           l ← lx  <br>
18:      end if  <br>
19: end for  <br>
20: return l <br>

