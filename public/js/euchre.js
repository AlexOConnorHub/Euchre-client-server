joinTable = async function(tableNumber) {
    stillFree = await fetch('/euchre/' + tableNumber).then(res => res.json()).then()
    if (!stillFree) {
        window.location.reload(true)
    } else {
        window.location.replace('/html/euchreGame.html')
    }
}

loadPage = async function() {
    tables = await fetch('/euchre/tables').then(res => res.json()).then()
    for (table of tables) {
        let newTable = templateButtons.cloneNode(true);
        newTable.classList.remove("template")
        newTable.childNodes[1].innerText = table + "/4"
        if (table == 1) {
            templateButtons.childNodes[1].scr = "/images/oneAtTable.png"
        } else if (table == 2) {
            templateButtons.childNodes[1].scr = "/images/twoAtTable.png"
        } else if (table == 3) {
            templateButtons.childNodes[1].scr = "/images/threeAtTable.png"
        } else if (table == 4) {
            templateButtons.childNodes[1].scr = "/images/fourAtTable.png"
        }
        document.getElementById("tables").appendChild(newTable);
    }
}