

<title>Encuestas Serbatic 2024</title>
<!-- Enlazamos con Bootstrap CDN -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
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

.crear-encuesta-btn {
	display: none;
	margin-top: 20px;
}

footer {
	margin-top: 40px;
	text-align: center;
	color: #78C0E0;
}

.encuesta-item {
	border: 1px solid #ddd;
	padding: 15px;
	border-radius: 5px;
	margin-bottom: 15px;
	background-color: #fff;
}

.encuesta-item h5 {
	margin: 0;
}
</style>
</head>
<body>
	<!-- Barra de navegaciÃ³n -->
	<header class="py-3">
		<div
			class="container d-flex justify-content-between align-items-center">
			<img src="assets/img/serbatic_logo_black.svg" class="logo"
				width="150px">
			<h1>Encuestas Serbatic 2024</h1>
			<div class="login">
				<%
				//HttpSession //session = request.getSession();
				System.out.println(session.getAttribute("usuario"));
				if (session.getAttribute("usuario") != null) {%>
				

				<!-- Enlace para iniciar sesiÃƒÂ³n que redirige a login.jsp -->
				<form action="login" method="POST">
					<a href="logout">Cerrar Sersion</a> 
					<p>Bienvenido <%= ((modelo.Usuario) session.getAttribute("usuario")).getNombre() %></p>
					<%
					} else {
					%>
					<a href="login">Inicia sesión</a>
					<%
					}
					%>
				
			</div>
		</div>
	</header>

	<div class="container mt-4">
		<h2 class="mb-4">Lista de Encuestas</h2>

		<!-- Lista de encuestas -->
		<div class="row">
			<c:forEach var="encuesta" items="${encuestas}">
				<div class="col-md-4">
					<div class="encuesta-item">
						<h5>${encuesta.titulo}</h5>
						<p>${encuesta.descripcion}</p>
						<a href="verEncuesta.jsp?id=${encuesta.id}" class="btn btn-info">Ver
							Encuesta</a> <a href="listar-encuestas-disponibles"
							class="btn btn-info">Realizar Encuesta </a>
					</div>
				</div>
			</c:forEach>
		</div>
		<button id="generateSurvey" class="btn">Crear Encuesta</button>
		<div id="surveysContainer">
			<!-- Espacio para crear la encuesta -->
		</div>
		<!-- SecciÃ³n Acerca de -->
		<div class="mt-5">
			<h3>Acerca de</h3>
			<p>Encuestas Serbatic 2024 es una plataforma para crear y
				gestionar encuestas de manera sencilla.</p>
		</div>
	</div>

	<!-- Pie de pÃ¡gina -->
	<footer class="py-3">
		<p>© 2024 Encuestas Serbatic. Todos los derechos reservados.</p>
	</footer>

	<!-- Scripts de Bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

	<script>
        document.getElementById('generateSurvey').addEventListener('click', function() {
            const surveysContainer = document.getElementById('surveysContainer');
            
            const survey = document.createElement('div');
            survey.className = 'survey';

            const surveyTitle = document.createElement('h2');
            surveyTitle.textContent = 'Nueva Encuesta';
            survey.appendChild(surveyTitle);

            const addQuestionButton = document.createElement('button');
            addQuestionButton.textContent = 'Añadir Pregunta';
            survey.appendChild(addQuestionButton);

            addQuestionButton.addEventListener('click', function() {
                const questionContainer = document.createElement('div');
                questionContainer.className = 'question-container';

                const questionInput = document.createElement('input');
                questionInput.type = 'text';
                questionInput.placeholder = 'Escribe tu pregunta aquí';
                questionContainer.appendChild(questionInput);

                for (let i = 1; i <= 4; i++) {
                    const answerInput = document.createElement('input');
                    answerInput.type = 'text';
                    answerInput.placeholder = `Respuesta ${i}`;
                    questionContainer.appendChild(answerInput);
                }

                const finishButton = document.createElement('button');
                finishButton.textContent = 'Finalizar Pregunta';
                questionContainer.appendChild(finishButton);

                finishButton.addEventListener('click', function() {
                    const questionText = questionInput.value;
                    const answerInputs = questionContainer.querySelectorAll('input[type="text"]:not(:first-child)');
                    
                    // Reemplazar el input de la pregunta con un párrafo
                    const questionParagraph = document.createElement('p');
                    questionParagraph.textContent = questionText;
                    questionContainer.replaceChild(questionParagraph, questionInput);

                    // Reemplazar los inputs de respuestas con párrafos
                    answerInputs.forEach(input => {
                        const answerParagraph = document.createElement('p');
                        answerParagraph.textContent = input.value;
                        questionContainer.replaceChild(answerParagraph, input);
                    });

                    // Eliminar el botón de finalizar
                    questionContainer.removeChild(finishButton);
                });

                survey.appendChild(questionContainer);
            });

            surveysContainer.appendChild(survey);
        });
    </script>
</body>
</html>

