# introsde2017-final-project
# Adapter Service
**Final project | University of Trento**

Documentation about the Adapter Service: SOAP Web Service

## API
#### Creates a new profile and returns the oauth_token and oauth_secret for the new profile
```<soap:Envelope
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
    <soap:Body xmlns:m="http://ws.adapter.introsde/">
        <m:createPerson>
        	<personId>143</personId>
        </m:createPerson>
    </soap:Body>
</soap:Envelope>```

#### Returns the authentication information for an user
```<soap:Envelope
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
    <soap:Body xmlns:m="http://ws.adapter.introsde/">
        <m:getAuth_info>
        	<personId>143</personId>
        </m:getAuth_info>
    </soap:Body>
</soap:Envelope>```

#### Set the weight, the height and the goal weight of an user
```<soap:Envelope
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
    <soap:Body xmlns:m="http://ws.adapter.introsde/">
        <m:setInfo>
        	<user>
        		<auth_secret>d2edecee31c64a26afdf33e39a1b6d0e</auth_secret>
                <auth_token>2cd7bdd75ea24dc2992cc69ed4e6503e</auth_token>
        	</user>
        	<weight>80</weight>
        	<height>180</height>
        	<weight_goal>75</weight_goal>
        </m:setInfo>
    </soap:Body>
</soap:Envelope>```

#### Update the weight of an user
```<soap:Envelope
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
    <soap:Body xmlns:m="http://ws.adapter.introsde/">
        <m:weightUpdate>
        	<user>
        		<auth_secret>d2edecee31c64a26afdf33e39a1b6d0e</auth_secret>
                <auth_token>2cd7bdd75ea24dc2992cc69ed4e6503e</auth_token>
        	</user>
        	<weight>79</weight>
        </m:weightUpdate>
    </soap:Body>
</soap:Envelope>```

#### Get the list of exercises
```<soap:Envelope
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
    <soap:Body xmlns:m="http://ws.adapter.introsde/">
        <m:getExercises>
        </m:getExercises>
    </soap:Body>
</soap:Envelope>```

#### Get detailed nutritional information for the specified food
```<soap:Envelope
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
    <soap:Body xmlns:m="http://ws.adapter.introsde/">
        <m:getFood>
        	<foodId>1234</foodId>
        </m:getFood>
    </soap:Body>
</soap:Envelope>```

#### Conducts a search of the food database using the search expression specified
```<soap:Envelope
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
    <soap:Body xmlns:m="http://ws.adapter.introsde/">
        <m:searchFood>
        	<text>Tiramisu</text>
        </m:searchFood>
    </soap:Body>
</soap:Envelope>```

#### Returns the daily exercise entries for the user on a nominated date (0 = current day)
```<soap:Envelope
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
    <soap:Body xmlns:m="http://ws.adapter.introsde/">
        <m:getExerciseEntry>
        	<user>
        		<auth_secret>d2edecee31c64a26afdf33e39a1b6d0e</auth_secret>
                <auth_token>2cd7bdd75ea24dc2992cc69ed4e6503e</auth_token>
        	</user>
        	<date>0</date>
        </m:getExerciseEntry>
    </soap:Body>
</soap:Envelope>```

#### Records a change to a user's exercise diary entry for a current day
```<soap:Envelope
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
    <soap:Body xmlns:m="http://ws.adapter.introsde/">
        <m:editExerciseEntry>
        	<user>
        		<auth_secret>d2edecee31c64a26afdf33e39a1b6d0e</auth_secret>
                <auth_token>2cd7bdd75ea24dc2992cc69ed4e6503e</auth_token>
        	</user>
        	<id_exercise>9</id_exercise>
        	<minutes>60</minutes>
        </m:editExerciseEntry>
    </soap:Body>
</soap:Envelope>```

#### Takes the set of exercise entries and saves these entries as "template" entries for nominated days of the week (The days of the week specified as bits with Sunday being the 1st bit and Saturday being the last and then converted to an Int)
```<soap:Envelope
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
    <soap:Body xmlns:m="http://ws.adapter.introsde/">
        <m:saveTemplate>
        	<user>
        		<auth_secret>d2edecee31c64a26afdf33e39a1b6d0e</auth_secret>
                <auth_token>2cd7bdd75ea24dc2992cc69ed4e6503e</auth_token>
        	</user>
        	<days>127</days>
        </m:saveTemplate>
    </soap:Body>
</soap:Envelope>```

#### Saves the default exercise entries for the user on current day
```<soap:Envelope
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
    <soap:Body xmlns:m="http://ws.adapter.introsde/">
        <m:commitDay>
        	<user>
        		<auth_secret>d2edecee31c64a26afdf33e39a1b6d0e</auth_secret>
                <auth_token>2cd7bdd75ea24dc2992cc69ed4e6503e</auth_token>
        	</user>
        </m:commitDay>
    </soap:Body>
</soap:Envelope>```
