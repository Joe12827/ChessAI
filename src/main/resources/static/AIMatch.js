if (document.readyState !== 'loading') {
    console.log('document is already ready, just execute code here');
    aiMatch();
} else {
    document.addEventListener('DOMContentLoaded', function () {
        console.log('document was not ready, place code here');
        aiMatch();
    });
}


function aiMatch() {

    console.log("START AI MATCH");

    const squares = document.querySelectorAll('.chess-board td');
    let startSquareId = null;
    let move = [];
    const moveSound = new Audio('/Sounds/move-self.mp3');
    const castleSound = new Audio('/Sounds/castle.mp3');
   
    // while(true) {
        console.log("turn");
        fetch('/api/board/getaimove')
        .then(response => {
            if (response.ok) {
                moveSound.play();
                return response.json();
            } else {
                throw new Error('Failed to fetch board state');
            }
        })
        .then(data => {
            console.log("AI MOVE: " + data);
            var xStart = data.start[0];
            var yStart = data.start[1] + 1;
            var xStop = data.stop[0];
            var yStop = data.stop[1] + 1;

            console.log(String.fromCharCode('a'.charCodeAt(0) + xStart) + yStart);
            console.log(String.fromCharCode('a'.charCodeAt(0) + xStop) + yStop);


            const startSquare = document.getElementById(String.fromCharCode('a'.charCodeAt(0) + xStart) + yStart);
            const stopSquare = document.getElementById(String.fromCharCode('a'.charCodeAt(0) + xStop) + yStop);

            const draggedPiece = startSquare.querySelector('.chess-board td img');
            const existingPiece = stopSquare.querySelector('.chess-board td img');

            if (startSquare.querySelector('.chess-board td img').alt == 'wk' || startSquare.querySelector('.chess-board td img').alt == 'bk') {
                console.log("MOVED KING");
                console.log(xStart + "," + yStart + " > " + xStop + "," + yStop)
                var newKingX = 0;
                var newKingY = 0;
                var newRookX = 0;
                var newRookY = 0;
                var castle = false;
                if (xStart == 4 && yStart == 1 && xStop == 7 && yStop == 1) { // Right White Castle
                    newKingX = 6;
                    newKingY = 1;
                    newRookX = 5;
                    newRookY = 1;
                    castle = true;
                } else if (xStart == 4 && yStart == 1 && xStop == 0 && yStop == 1) { // Left White Castle
                    newKingX = 2;
                    newKingY = 1;
                    newRookX = 3;
                    newRookY = 1;
                    castle = true;
                } else if (xStart == 4 && yStart == 8 && xStop == 7 && yStop == 8) { // Right Black Castle
                    newKingX = 6;
                    newKingY = 8;
                    newRookX = 5;
                    newRookY = 8;
                    castle = true;
                } else if (xStart == 4 && yStart == 8 && xStop == 0 && yStop == 8) { // Left Black Castle
                    newKingX = 2;
                    newKingY = 8;
                    newRookX = 3;
                    newRookY = 8;
                    castle = true;
                }

                if (castle) {
                    castleSound.play();
                    const newKingSquare = document.getElementById(String.fromCharCode('a'.charCodeAt(0) + newKingX) + newKingY);
                    const newRookSquare = document.getElementById(String.fromCharCode('a'.charCodeAt(0) + newRookX) + newRookY);

                    newKingSquare.appendChild(draggedPiece);
                    newRookSquare.appendChild(existingPiece);


                } else {
                    moveSound.play();
                    if (existingPiece) {
                        existingPiece.remove(); // Remove the existing piece image
                    }
                    stopSquare.appendChild(draggedPiece);
                }
            } else {
                moveSound.play();
                if (existingPiece) {
                    existingPiece.remove(); // Remove the existing piece image
                }
                stopSquare.appendChild(draggedPiece);
            }
        })
    }
// }