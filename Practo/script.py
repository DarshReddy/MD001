import requests

url = 'https://www.omdbapi.com/?apikey=cd407587&';

title = input("Enter movie title: ")
PARAMS = {'t':title}
r = requests.get(url = url, params = PARAMS)
data = r.json()
if(data['Response'] == 'False'):
  print(data['Error'])
  exit()
final_title = data['Title']
if(data['Ratings'] != []):
  rating = data['Ratings'][0]['Value']
else:
  rating = 0
print(final_title,':' ,rating, ' stars')