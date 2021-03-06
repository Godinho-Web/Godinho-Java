listar();
var idjogo = 0;
var myModal = new bootstrap.Modal(
  document.getElementById('cadastro')
);


function listar(){
  var requestOptions = {
    method: 'GET',
    redirect: 'follow'
  };

  fetch("http://localhost:8080/JogosRest/JogosRest", requestOptions)
    .then(response => response.json())
    .then(function(result){
      var dados = "<th>Descricao</th>";
          dados += "<th>Tipo</th>";
          dados += "<th>Data Lancamento</th>";

      for (const i in result) {

        dados += "<tr>"
            + "<td>" + result[i].descricao + "</td>"
            + "<td>" + result[i].tipo + "</td>"
            + "<td>" + result[i].datalanc + "</td>"
            + "<td><a class='btn btn-primary' onclick='alterar(" + result[i].idjogo + ")'>Alterar</a></td>"
            + "<td><a class='btn btn-danger' onclick='excluir(" + result[i].idjogo + ")'>Excluir</a></td>"
            + "</tr>";

      }
      document.getElementById("dados").innerHTML = dados;
    })
    .catch(error => console.log('error', error));
}

function alterar(id){
  idjogo = id;
  var requestOptions = {
    method: 'GET',
    redirect: 'follow'
  };
   
  fetch("http://localhost:8080/JogosRest/JogosRest/" + idjogo, requestOptions)
    .then(response => response.json())
    .then(function(result){
      document.getElementById("descricao").value = result.descricao;
      document.getElementById("tipo").value = result.tipo;
      document.getElementById("datalanc").value = result.datalanc;
      myModal.show();
    })
    .catch(error => console.log('error', error));
}

function excluir(idjogo){
  var raw = "";
 
  var requestOptions = {
    method: 'DELETE',
    body: raw,
    redirect: 'follow'
  };
  fetch("http://localhost:8080/JogosRest/JogosRest/" + idjogo, requestOptions)
  .then(response => response.text())
  .then(function(result){
    listar();
  })
  .catch(error => console.log('error', error));
}

function novo(){
  idpessoa = 0;
  document.getElementById("descricao").value = "";
  document.getElementById("tipo").value = "";
  document.getElementById("datalanc").value = "";
  myModal.show();
}

function salvar(){
  var metodo;
  if (idjogo>0){
    metodo = "PUT";
  } else {
    metodo = "POST";
  }

 // var raw = "{\r\n    \"nome\": \"Maria\",\r\n    \"telefone\": \"35 99145-6532\",\r\n    \"email\": \"maria@gmail.com\"\r\n}";
  var j = {};
  j.idjogo = idjogo;
  j.descricao = document.getElementById("descricao").value;
  j.tipo = document.getElementById("tipo").value;
  j.datalanc = document.getElementById("datalanc").value;

  var raw = JSON.stringify(j);
  console.log(raw);
  if ((j.descricao == "") || (j.tipo == "") || (j.datalanc == "")){
    alert("nome ?? obrigat??rio");
    return;
  }

  var requestOptions = {
    method: metodo,
    body: raw,
    redirect: 'follow'
  };
 
  fetch("http://localhost:8080/JogosRest/JogosRest", requestOptions)
    .then(response => response.text())
    .then(function (result){
      listar();
    })
    .catch(error => console.log('error', error));

    myModal.hide();
  }




