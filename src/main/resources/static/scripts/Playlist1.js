function chg() {

    if (btnPlay.src.indexOf("../static/Player/Play.png") > 0) {
        btnPlay.src = "../static/Player/Pause.png";
    } else {
        btnPlay.src = "../static/Player/Play.png";
    }
    if (mainPlay.src.indexOf("../static/Player/Play.png") > 0) {
        mainPlay.src = "../static/Player/Pause.png";
    } else {
        mainPlay.src = "../static/Player/Play.png";
    }
}


/*let btns = [
    document.getElementById('OneTrackPL'),
    document.getElementById('TwoTrackPL'),
    document.getElementById('ThreeTrackPL'),
    document.getElementById('FourTrackPL'),
    document.getElementById('FiveTrackPL'),
    document.getElementById('SixTrackPL'),
    document.getElementById('SevenTrackPL'),
    document.getElementById('EightTrackPL'),
    document.getElementById('NineTrackPL'),
    document.getElementById('TenTrackPL'),

];*/

let btns = document.querySelectorAll(".Track");

let count = 0;
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

for (let i =0; i< buttons.length;i++){
    buttons[i].addEventListener("click", function () {
        isThis = audio;
        change(i);

    });
}

/*
buttons[0].addEventListener("click", function () {
    isThis = audio;
    change(0);

});
buttons[1].addEventListener("click", function () {
    isThis = audio;
    change(1);
});
buttons[2].addEventListener("click", function () {
    isThis = audio;
    change(2);

});
buttons[3].addEventListener("click", function () {
    isThis = audio;
    change(3);

});
buttons[4].addEventListener("click", function () {
    isThis = audio;
    change(4);

});
buttons[5].addEventListener("click", function () {
    isThis = audio;
    change(5);

});
buttons[6].addEventListener("click", function () {
    isThis = audio;
    change(6);
});
buttons[7].addEventListener("click", function () {
    isThis = audio;
    change(7);

});
buttons[8].addEventListener("click", function () {
    isThis = audio;
    change(8);

});
buttons[9].addEventListener("click", function () {
    isThis = audio;
    change(9);

});*/

mainPlay.addEventListener("click", function () {
    if (isPlay === false) {

        audio.play();
        isPlay = true;
    }
    else
    if (isPlay === true) {
        audio.pause();
        isPlay = false;
    }
});

function change(ind) {
    audio = audios[ind];
    audio.volume = rangeVolume.value / 100;
    slider.setAttribute('max', audio.duration);
    if (audio !== isThis) {
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
