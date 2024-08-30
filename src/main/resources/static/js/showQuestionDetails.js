// ポップアップを表示する関数
function showPopup() {
    document.querySelector('.confirmPopUp').style.display = 'block';
    document.querySelector('.overlay').style.display = 'block';
}

// はいボタンがクリックされたときの処理
document.querySelector('.agree-btn').addEventListener('click', function () {
    document.querySelector('.overlay').style.display = 'none';
});

// 「いいえ」ボタンと「✖」ボタンがクリックされたときの処理
document.querySelectorAll('.cancel-btn, .close-btn').forEach(function (btn) {
    btn.addEventListener('click', function () {
        document.querySelector('.confirmPopUp').style.display = 'none';
        document.querySelector('.overlay').style.display = 'none';
    });
});
