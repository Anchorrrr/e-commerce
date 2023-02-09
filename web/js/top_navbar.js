var womenCates;
var menCates;
var kidsCates;
var managerTabs;
var mask;
window.screen.height;
window.onload = function ()
{
    womenCates = document.getElementById("womenCates");
    menCates = document.getElementById("menCates");
    kidsCates = document.getElementById("kidsCates");
    managerTabs = document.getElementById("managerTabs")
    mask = document.getElementById("mask")
    mask.style.cssText = "height:" + document.body.scrollHeight + "px";
    mask.style.display = "none";
}
function closeCates()
{
    womenCates.style.display="none";
    menCates.style.display="none";
    kidsCates.style.display="none";
    managerTabs.style.display="none";
    mask.style.display="none";
}
function womenTabMouseEnter()
{
    womenCates.style.display="block";
    mask.style.display="block";
    menCates.style.display="none";
    kidsCates.style.display="none";
    managerTabs.style.display="none";
}
function menTabMouseEnter()
{
    menCates.style.display="block";
    mask.style.display="block";
    womenCates.style.display="none";
    kidsCates.style.display="none";
    managerTabs.style.display="none";
}
function kidsTabMouseEnter()
{
    kidsCates.style.display="block";
    mask.style.display="block";
    menCates.style.display="none";
    womenCates.style.display="none";
    managerTabs.style.display="none";
}
function managerTabMouseEnter()
{
    managerTabs.style.display="block";
    mask.style.display="block";
    menCates.style.display="none";
    womenCates.style.display="none";
    kidsCates.style.display="none";
}