function submitAnswer(event, answer) {
    fetch('/quizApplication/question/evaluateAnswer', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({ 'answer': answer })
    })
    .then(response => response.json())
    .then(result => {
        document.getElementById('resultMessage').textContent = result.message;
        document.getElementById('nextQuestionBtn').style.display = 'inline-block';
        document.querySelectorAll('.option').forEach(button => button.disabled = true);
        if (result.lastQuestion) {
            document.getElementById('nextQuestionBtn').textContent = '結果を見る';
            document.getElementById('nextQuestionForm').setAttribute('action', '/quizApplication/question/finalResult');
        }
    })
    .catch(error => console.error('Error:', error));
}

