package com.example.myfirstretro


data class Books ( var isbn : String,
var title : String,
var subtitle :String,
var author : String,
var published : String,
var publisher : String,
var pages : Int,
var description : String,
var website : String)





//1. Form with all the fields from the entity  - done
//2. when user submits it get all the filled data -
//  create a jsonObject/Gson using the data filled
//3. convert that json to string .toString
//4. convert the string to .toRequestBody



//Form   // get and set value
//1. Label
//2. TextBox - EditText
//3. Radio button - choose only one
//4. Checkbox - can choose multiple options
//5. Select - dropdown (one or more)
//6. Autocomplete -
//7. Date Picker - month view calendar to pick date
//8. File Upload - RAID - Redundant Array of Independent disk (s3)
// HADR - High Availability and Disaster Recovery
//9. Map



//Pagination

//getAll --> pagenum = 1
// select * from
// sql query limit ( where to end) and offset ( where to start)
// load More


//0 => 10
//p=0 offset = 0, p= 1 offset = 10, p=2 ofsset = 20
//offset =  (p*limit)
//limit = 10
//
//1 => 10
//offset = (p-1)*limit
//limit =10
//p=1 offset= 0, p=2 offset =10, p=3 offset = 20
//
//p=2
//     offset = 10
//     limit = 10




















