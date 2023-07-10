function search(event) {
  if(event.keyCode == 13) {
    getWeather();
    getForecast();
  }
}

function getWeather() {
  const xhr = new XMLHttpRequest();
  const cityInput = document.getElementById("city-input");
  const city = cityInput.value;
console.log(city);
  // Make an API call to retrieve weather data for the city
  // Replace the URL with your actual API endpoint
const url = 'http://192.168.1.3:8090/weather/'+city;
console.log(url);
// console(xhr.getResponseHeader());
xhr.open('GET',url,true);
xhr.send();

xhr.onload = () =>{
  
  // we can change the data type to json also by
  console.log(xhr.status);
 if(xhr.status == 200){
  const data = JSON.parse(xhr.response);
  console.log(data);
  const weatherInfo = document.getElementById("weather-info");
  console.log(weatherInfo);

    document.getElementById("current").innerHTML = `Current Weather`;
    document.getElementById("city").innerHTML = data.name;
    document.getElementById("temp").innerHTML = `${data.main.temp}<img src="https://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png" />`;
    document.getElementById("humd").innerHTML = `Humidity: ${data.main.humidity}%`;
    document.getElementById("desc").innerHTML = `Description: ${data.weather[0].description}`;

 }
 else{
    alert("Incorrect City "+xhr.status.toString());
    alert("Please enter correct City");
    window.location.reload();
 }
  
};



}

function getForecast(){
  var container = document.getElementById("forecast-container");
  container.innerHTML = "";
  const xhr = new XMLHttpRequest();
  const cityInput = document.getElementById("city-input");
  const city = cityInput.value;
  console.log(city.length);
  const url = 'http://192.168.1.3:8083/forecast/'+city;

  console.log(url);
  xhr.open('GET',url,true);
  xhr.send();

  xhr.onload = () => {

    console.log('Status : '+xhr.status);

    if(xhr.status == 200){
      var data = JSON.parse(xhr.response);
  
var forecastContainer = document.getElementById("forecast-container");

// Clear the container
forecastContainer.innerHTML = "";
document.getElementById("currentF").innerHTML = `3 hours Forecast`;
     data.list.forEach(element => {
 
// Display forecast
  var forecastElement = document.createElement("div");
  forecastElement.classList.add("forecast-item");

  var timeElement = document.createElement("p");
  timeElement.textContent = timeConverter(element.dt_txt);

  var temperatureElement = document.createElement("p");
  temperatureElement.textContent = element.main.temp;
  var imgElement = document.createElement("img")
  imgElement.src=`https://openweathermap.org/img/wn/${element.weather[0].icon}@2x.png`
  temperatureElement.appendChild(imgElement);

  var weatherConditionElement = document.createElement("p");
  weatherConditionElement.textContent = element.weather[0].description;

  forecastElement.appendChild(timeElement);
  forecastElement.appendChild(temperatureElement);
  forecastElement.appendChild(weatherConditionElement);

  forecastContainer.appendChild(forecastElement);
  });
}

    else if (xhr.status == 404 || city.length == 0  ){
      console.log(xhr.status)
      alert("Incorrect City "+xhr.status.toString());
      alert("Please enter correct City");
      window.location.reload();
    }
   
  }
}

function timeConverter(isoTime){
  const timestamp = new Date(isoTime).getTime() / 1000; // Convert to UNIX timestamp
  const istOffset = 5.5 * 60 * 60; // IST offset in seconds
  const istTimestamp = timestamp + istOffset; // Add IST offset
  const istDate = new Date(istTimestamp * 1000); // Convert back to JavaScript Date object
  return istDate.toLocaleString("en-US", { timeZone: "Asia/Kolkata" }); 
}
