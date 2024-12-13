<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Encuesta y Pregunta</title>
    <!-- Enlace a Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f4f4;
        }
        header {
            background-color: #78C0E0;
            color: black;
        }
        header .login {
            margin: 0;
        }
        footer {
            margin-top: 40px;
            text-align: center;
            color: #78C0E0;
        }
        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-top: 30px;
        }
        .form-container h1 {
            margin-bottom: 20px;
        }
        .form-container input {
            margin-bottom: 10px;
        }
        .form-container button {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <!-- Barra de navegación -->
    <header class="py-3">
        <div class="container d-flex justify-content-between align-items-center">
            <img src="assets/img/serbatic_logo_black.svg" class="logo" width="150px">
            <h1>Encuestas Serbatic 2024</h1>
            <div class="login">
                <!-- Enlace para iniciar sesión que redirige a login.jsp -->
                <form action="login" method="POST">
                    <button type="submit" class="btn btn-primary">Inicia sesión</button>
                </form>
            </div>
        </div>
    </header>

    <!-- Contenedor del formulario de creación de encuesta -->
    <div class="container form-container">
        <h1>Crear Nueva Encuesta</h1>

        <!-- Mostrar mensaje de error si existe -->
        <% if (request.getAttribute("mensajeError") != null) { %>
            <div class="error">
                <p><%= request.getAttribute("mensajeError") %></p>
            </div>
        <% } %>

        <!-- Mostrar mensaje de éxito si existe -->
        <% if (request.getAttribute("mensajeExito") != null) { %>
            <div class="success">
                <p><%= request.getAttribute("mensajeExito") %></p>
            </div>
        <% } %>

        <!-- Formulario para crear encuesta 
        <form action="crear-encuesta" method="post">

            <div class="form-group">

                <label for="nombreEncuesta">Nombre de la Encuesta:</label>
                <input type="text" class="form-control" id="nombreEncuesta" name="nombreEncuesta" required>

            </div>

            <div class="form-group">
                <label for="fechaCaducidad">Fecha de Caducidad:</label>
                <input type="date" id="fechaCaducidad" name="fechaCaducidad" required>
            </div>
            <button type="submit">Crear Encuesta</button>
        </form>
    </div>-->
    
        <!-- Formulario para crear encuesta -->
    <form action="crear-encuesta"  method="post" id="formularioEncuesta" target="iframe-oculto">
        <div class="form-group">
            <label for="nombreEncuesta">Nombre de la Encuesta:</label>
            <input type="text" class="form-control" id="nombreEncuesta" name="nombreEncuesta" required>
        </div>
        <div class="form-group">
            <label for="fechaCaducidad">Fecha de Caducidad:</label>
                <input type="date" id="fechaCaducidad" name="fechaCaducidad" required>
        </div>
            <button type="submit" id="generateSurvey">Generar Nueva Encuesta</button>    
    </form>
    <div id="surveysContainer"></div>
    <iframe name="iframe-oculto" style="display:none"></iframe>
           
           <% 
			  if (session.getAttribute("idEncuesta")!=null) { %>
			   <input type='hidden' id="miIdEncuestaActual" value="<%= session.getAttribute("idEncuesta") %>">
			
			<% } %>
              <!-- Mensajes de error -->
           <% 
			    String error = (String) request.getAttribute("error");
			    if (error != null) { 
			%>
			    <div>
			        <p><%= error %></p>
			    </div>
			<% } %>

    <!-- Pie de página -->
    <footer class="py-3">
        <p>&copy; 2024 Encuestas Serbatic. Todos los derechos reservados.</p>
    </footer>

    <script>
        document.getElementById('generateSurvey').addEventListener('click', function(e) {
            
            const surveysContainer = document.getElementById('surveysContainer');
            
            const survey = document.createElement('div');
            survey.className = 'survey';

            const surveyTitle = document.createElement('h2');
            surveyTitle.textContent =  document.getElementById("nombreEncuesta").value;
            survey.appendChild(surveyTitle);

            const surveyAliveTime = document.createElement('p');
            surveyAliveTime.textContent = "Activa hasta: " + document.getElementById("fechaCaducidad").value;
            survey.appendChild(surveyAliveTime)

            const addQuestionButton = document.createElement('button');
            addQuestionButton.textContent = 'Añadir Pregunta';
            survey.appendChild(addQuestionButton);

            addQuestionButton.addEventListener('click', function() {
                const questionContainer = document.createElement('div');
                questionContainer.className = 'container form-container';

                const questionForm = document.createElement("form");
                questionForm.action = "crear-pregunta";
                questionForm.method = "post";
                questionContainer.appendChild(questionForm)
                
                const divBootStyle = document.createElement("div");
                divBootStyle.className = "mb-3";
                questionForm.appendChild(divBootStyle);

                const labelQuestionText = document.createElement('label');
                labelQuestionText.for ="textoPregunta";
                labelQuestionText.className = 'form-label';
                labelQuestionText.textContent = 'Texto de la pregunta: ';
                divBootStyle.appendChild(labelQuestionText);

                const questionInput = document.createElement('input');
                questionInput.type = 'text';
                questionInput.placeholder = 'Escribe tu pregunta aquí';
                questionInput.className = "form-control";
                questionInput.name = "pregunta";
                questionInput.id = 'pregunta';
                divBootStyle.appendChild(questionInput);

                //momento añadir preguntas

                for (let i = 1; i <= 4; i++) {
                    const divBootsStyleA = document.createElement('div');
                    divBootsStyleA.className = "mb-3";
                    const answerLabel = document.createElement('label');
                    answerLabel.for = 'res1${formularioContador}';
                    answerLabel.className = 'form-label;'
                    answerLabel.textContent = `Respuesta ${i}:`;
                    divBootsStyleA.appendChild(answerLabel);

                    const answerInput = document.createElement('input');
                    answerInput.type = 'text';
                    answerInput.placeholder = `Respuesta ${i}`;
                    answerInput.className = 'form-control';
                    answerInput.name = 'respuesta';
                    divBootsStyleA.appendChild(answerInput);
                    questionForm.appendChild(divBootsStyleA);
                }

                const hideIdInput = document.createElement('input');
                hideIdInput.type = 'hidden';
                hideIdInput.name = 'idEncuesta';
                
                //
                hideIdInput.value = document.getElementById("miIdEncuestaActual").value;
                questionForm.appendChild(hideIdInput);

                const finishButton = document.createElement('button');
                finishButton.textContent = 'Finalizar Pregunta';
                finishButton.type = 'submit';
                finishButton.class = 'btn btn.success';
                finishButton.target = 'iframe-oculto'
                questionForm.appendChild(finishButton);
        
                finishButton.addEventListener('click', function(e) {
                    
                    /*const questionText = questionInput.value;
                    const answerInputs = questionContainer.querySelectorAll('input[type="text"]:not(:first-child)');
                    
                    // Reemplazar el input de la pregunta con un párrafo
                    const questionParagraph = document.createElement('p');
                    questionParagraph.textContent = questionText;
                    questionForm.replaceChild(questionParagraph, questionInput);

                    // Reemplazar los inputs de respuestas con párrafos
                    answerInputs.forEach(input => {
                        const answerParagraph = document.createElement('p');
                        answerParagraph.textContent = input.value;
                        questionForm.replaceChild(answerParagraph, input);
                    });*/

                    // Eliminar el botón de finalizar
                    questionForm.removeChild(finishButton);
                });

                survey.appendChild(questionContainer);
            });

            //surveysContainer.appendChild(survey);
        });
    </script>
    <!-- Scripts de Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>

