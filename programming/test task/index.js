var progress = document.getElementById("progress");
                        
var maxValue = progress.max;
var value = progress.value;
var valueProsenti = Number(value)/maxValue*100;
console.info(valueProsenti);
var valuePixel = valueProsenti * 110 / 100;
console.info(valuePixel);

var progressBegunok = document.getElementById("progress-begunok");
var progressValueText = document.getElementById("progress-value-text");

progressBegunok.style.left = valuePixel + 'px';
progressValueText.textContent = String(value);
                

var difference = document.getElementById("difference");
difference.textContent = String(maxValue - value);
console.log(difference);