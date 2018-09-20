Application:

This Application created to find that two cities are connected by roads.
When the application loads it takes all the connected cities from a flat file

Requesting:
To Invoke this service you have to hit the following URL

http://localhost:8080/connected?origin=chennai&destination=mumbai

The Request gives the origin - the source of the connection 
and destination - the end city.

Mandatory:
Origin and Destination are mandatory fields.
If not provide you will get an Error Message

City Names:
The city names can be passed in CAPS or Small case letters
The service does not differntiate based on this.
If you pass invalid names then you will always get a result 
as there is no connection between the cities

Success:
On a successful result, you will get the Origin, Destination and whether they are connected.
The Result will be in the form of JSON with the following format

{  "origin":"chennai",
   "destination":"mumbai",
   "connected":true
}

Failure:
If the request is have invalid URL or If any of the origin/destionation values are missing you
will get the following error output

"You Are Seeing this error Page. Because You Are Requesting a Non Existing Resource OR Mandatory Paramenter Are not Passed with /connected"
