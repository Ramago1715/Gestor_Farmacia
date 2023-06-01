function sendLogin() {
  var http = new XMLHttpRequest();
  var email = document.getElementById('email').value;
  var pass = document.getElementById('pass').value;

  http.open("POST", "http://localhost:8067/Farmacia/Login", true);
  http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

  http.onreadystatechange = function () {
    if (http.readyState == 4 && http.status == 200) {
      var respuesta = http.responseText;
      document.getElementById("email").value = "";
      document.getElementById("pass").value = "";

      if (respuesta == "null") {
        console.log("El login no ha sido correcto");
        document.getElementById("respuesta").innerHTML = "Credenciales erroneas";
      } else {
        console.log("El login ha sido correcto");
        sessionStorage.setItem('session', respuesta);
        sessionStorage.setItem('email', email);
        window.location.href = 'Gestio.html';
      }
    }
  }


  http.send("correo=" + encodeURIComponent(email) + "&pass=" + encodeURIComponent(pass));
  http.abort;
}



function logOut() {
  sessionStorage.removeItem('session');
  sessionStorage.removeItem('email');
  window.location.href = 'Login.html';
}
function alta() {
  window.location.href = 'Alta.html';
}
function getTable() {
  var mail = sessionStorage.getItem('email');
  var session = sessionStorage.getItem('session');
  var thttp = new XMLHttpRequest();

  thttp.open("POST", "http://localhost:8067/Farmacia/ServeXips", true);
  thttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  thttp.onreadystatechange = function () {
    if (thttp.readyState == 4 && thttp.status == 200) {
      var respuesta = thttp.response
      if (respuesta == "null") {
        sessionStorage.removeItem('session');
        sessionStorage.removeItem('email');
        window.location.href = 'Login.html';
      } else {
        document.getElementById("tabla").innerHTML = respuesta;

      }


    }
  }


  thttp.send("mail=" + encodeURIComponent(mail) + "&session=" + encodeURIComponent(session));
}

function gestion() {
  window.location.href = "Gestio.html";
}

function getpatient() {
  var mail = sessionStorage.getItem("email");
  var session = sessionStorage.getItem('session');
  var http = new XMLHttpRequest();

  http.open("POST", "http://localhost:8067/Farmacia/Spatient", true);
  http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

  http.onreadystatechange = function () {
    if (http.readyState == 4 && http.status == 200) {
      var respuesta = http.response;
      if (respuesta == "null") {
        sessionStorage.removeItem('session');
        sessionStorage.removeItem('email');
        window.location.href = 'Login.html';
      } else {
        json = JSON.parse(respuesta);
        var selectpacientes = document.getElementById("pacientes");
        for (var i = 0; i < json.length; i++) {
          paciente = json[i].mail;
          var option = document.createElement("option");
          option.text = paciente;
          option.value = paciente;
          selectpacientes.appendChild(option);

        }
      }
    }
  }
  http.send("mail=" + encodeURIComponent(mail) + "&session=" + encodeURIComponent(session))
}



function getmedicine() {
  var mail = sessionStorage.getItem("email");
  var session = sessionStorage.getItem('session');
  var http = new XMLHttpRequest();

  http.open("POST", "http://localhost:8067/Farmacia/Smedicine", true);
  http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

  http.onreadystatechange = function () {
    if (http.readyState == 4 && http.status == 200) {
      var respuesta = http.response;
      if (respuesta == "null") {
        sessionStorage.removeItem('session');
        sessionStorage.removeItem('email');
        window.location.href = 'Login.html';
      } else {
        json = JSON.parse(respuesta);
        var selectmedicine = document.getElementById("medicamentos");
        for (var i = 0; i < json.length; i++) {
          medicamentos = json[i].idmedicamento;
          nombre = json[i].nombremedicamento  
          var option = document.createElement("option");
          option.value = medicamentos;
          option.text = nombre;
          
          selectmedicine.appendChild(option);

        }
        
      }
    }
  }
  http.send("mail=" + encodeURIComponent(mail) + "&session=" + encodeURIComponent(session))
}


function cargarfunciones(){
  getpatient();
  getmedicine();
}


function enviar(){
  var mail = sessionStorage.getItem("email");
  var session = sessionStorage.getItem('session');
  var http = new XMLHttpRequest();
  http.open("POST", "http://localhost:8067/Farmacia/Release", true);
  http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

  http.onreadystatechange = function () {
    if (http.readyState == 4 && http.status == 200) {
      var respuesta = http.response;
      if (respuesta == "null") {
        sessionStorage.removeItem('session');
        sessionStorage.removeItem('email');
        window.location.href = 'Login.html';
      }
      else{
        document.getElementById("pacientes").value = "";
        document.getElementById("medicamentos").value = "";
        document.getElementById("fechalimite").value = "";
        document.getElementById("idxips").value = "";
        document.getElementById("altacorr").innerHTML = "Enviado correctamente a la base de datos";
      }
    }
  }

  paciente = document.getElementById("pacientes").value;
  medicamento = document.getElementById("medicamentos").value;
  fecha = document.getElementById("fechalimite").value;
  idxips = document.getElementById("idxips").value;
  http.send("mail=" + encodeURIComponent(mail) + "&session=" + encodeURIComponent(session) + "&medicamento=" + encodeURIComponent(medicamento) + "&paciente=" 
  + encodeURIComponent(paciente) + "&fechalimite=" + encodeURIComponent(fecha) + "&idxips=" + encodeURIComponent(idxips));

  /*idXip, idMed, date i mailP*/

}