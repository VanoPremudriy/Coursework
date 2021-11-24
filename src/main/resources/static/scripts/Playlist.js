function chg() {

    if (btnPlay.src.indexOf("Player/Play.png") > 0) {
        btnPlay.src = "../static/Player/Pause.png";
    } else {
        btnPlay.src = "../static/Player/Play.png";
    }
    if (mainPlay.src.indexOf("Player/Play.png") > 0) {
        mainPlay.src = "../static/Player/Pause.png";
    } else {
        mainPlay.src = "../static/Player/Play.png";
    }
}


let count = 0;
let btns = document.querySelectorAll('.Track');
let audio = document.getElementById('Audio');
let buttons = document.querySelectorAll(".TenImage");
let audios = document.querySelectorAll(".Audio");
let mainPlay = document.getElementById('Play');
let btnPlay = mainPlay;

let isThis;
let slider = document.getElementById('myRange');
let isPlay = true;
let line = document.getElementById('Line');
let rangeVolume = document.getElementById('myRangeTwo');
rangeVolume.value = 20;

for (let i = 0; i < buttons.length; i++) {
    buttons[i].addEventListener("click", function () {
        isThis = audio;
        change(i);

    });
}


mainPlay.addEventListener("click", function () {
    if (isPlay == false) {

        audio.play();
        isPlay = true;
    }
    else
    if (isPlay == true) {
        audio.pause();
        isPlay = false;
    }
});

function change(ind) {
    audio = audios[ind];
    audio.volume = rangeVolume.value / 100;
    slider.setAttribute('max', audio.duration);
    if (audio != isThis) {
        slider.value = 0;
        audio.currentTime = 0;
        if (isPlay) {
            isThis.pause();
            chg();
            audio.play();
            btnPlay = btns[ind];
            chg();
        }
        else {
            isPlay = true;
            audio.play();
            btnPlay = btns[ind];
            chg();
        }
    }
    else {
        if (isPlay) {
            audio.pause()
            isPlay = false;
            chg();
        }
        else {
            audio.play();
            isPlay = true;
            chg();
        }
    }
}


slider.addEventListener('input', function (event) {
    audio.currentTime = slider.value;
});

rangeVolume.addEventListener('input', function (event) {
    audio.volume = rangeVolume.value / 100;
});


