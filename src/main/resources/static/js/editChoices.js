function showPopup() {
    document.querySelector('.overlay').style.display = 'block';
    const popup = document.querySelector('.confirmPopUp').style.display = 'block';

    const questionInput = document.getElementById('question').value;
    const choice1Input = document.getElementById('choice1').value;
    const choice2Input = document.getElementById('choice2').value;
    const choice3Input = document.getElementById('choice3').value;
    const choice4Input = document.getElementById('choice4').value;

    const afterRow = document.querySelector('.confirmPopUp .after');
    afterRow.children[1].textContent = questionInput;
    afterRow.children[2].textContent = choice1Input;
    afterRow.children[3].textContent = choice2Input;
    afterRow.children[4].textContent = choice3Input;
    afterRow.children[5].textContent = choice4Input;
}

// キャンセルボタンのクリックイベントを処理する関数
document.querySelector('.cancel-btn').addEventListener('click', function () {
    document.querySelector('.overlay').style.display = 'none';
    document.querySelector('.confirmPopUp').style.display = 'none';
});

function submitUpdateForm() {
    var contextPath = '/quizApplication';
    document.getElementById('editForm').action = contextPath + '/edit/update';

    // フォームを送信する
    document.getElementById('editForm').submit();
}
