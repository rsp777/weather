function search() {
  if(event.keyCode == 13) {
    getWeather();
  }
}
function getWeather() {
  const xhr = new XMLHttpRequest();
  const cityInput = document.getElementById("city-input");
  const city = cityInput.value;
console.log(city);
  // Make an API call to retrieve weather data for the city
  // Replace the URL with your actual API endpoint
  const url = 'http://localhost:8082/weather/'+city;
console.log(url);
xhr.open('GET',url);
xhr.send();



xhr.onload = () =>{
  
  // we can change the data type to json also by
  console.log(xhr.status);
 if(xhr.status == 200){
  const data = JSON.parse(xhr.response);
  console.log(data);
  const weatherInfo = document.getElementById("weather-info");
  console.log(weatherInfo);
//   weatherInfo.innerHTML = `
//  <h2>${data.name}</h2>
//     <p>Temperature: ${data.main.temp}°C</p>
//     <p>Humidity: ${data.main.humidity}%</p>
//     <p>Description: ${data.weather[0].description}</p>`

    document.getElementById("city").innerHTML = data.name;
    document.getElementById("temp").innerHTML = `<p>Temperature : ${data.main.temp}</p>`;
    document.getElementById("humd").innerHTML = `<p>Humidity: ${data.main.humidity}%</p>`;
    document.getElementById("desc").innerHTML = `<p>Description: ${data.weather[0].description}</p>`;

    console.log(document.getElementById("city") );
 }
 else{
    alert("Internal Server Error "+xhr.status.toString());
    alert("Please enter correct city");
 }
  
  
  
};



}