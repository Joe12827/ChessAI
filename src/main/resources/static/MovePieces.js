document.addEventListener('DOMContentLoaded', () => {
    const squares = document.querySelectorAll('.chess-board td');
    let startSquareId = null;
    let move = [];

    // Add event listeners to all squares
    squares.forEach(square => {
        // Event listener for when a piece is dragged over a square
        square.addEventListener('dragover', (event) => {
            event.preventDefault(); // Allow drop on this square
        });

        // Event listener for when a piece is dropped onto a square
        square.addEventListener('drop', (event) => {
            event.preventDefault(); // Prevent default behavior

            const draggedPiece = document.querySelector('.dragging');
            const move = {start: startSquareId, stop: square.id}

            fetch('/api/board/move', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(move)
            })
            .then(response => {
                if (response.ok) {
                    console.log('Move Executed Successfully');

                    
                    fetch('/api/board/lastmove')
                    .then(response => {
                        if (response.ok) {
                            return response.json();
                        } else {
                            throw new Error('Failed to fetch board state');
                        }
                    })
                    .then(data => {
                        console.log("LAST MOVE: " + data);
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
                                console.log("CASTLE!");
                                const newKingSquare = document.getElementById(String.fromCharCode('a'.charCodeAt(0) + newKingX) + newKingY);
                                const newRookSquare = document.getElementById(String.fromCharCode('a'.charCodeAt(0) + newRookX) + newRookY);

                                newKingSquare.appendChild(draggedPiece);
                                newRookSquare.appendChild(existingPiece);


                            } else {
                                if (existingPiece) {
                                    existingPiece.remove(); // Remove the existing piece image
                                }
                                stopSquare.appendChild(draggedPiece);
                            }
                        } else {
                            if (existingPiece) {
                                existingPiece.remove(); // Remove the existing piece image
                            }
                            stopSquare.appendChild(draggedPiece);
                        }
                    })


                    fetch('/api/board/getaimove')
                    .then(response => {
                        if (response.ok) {
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
                        if (existingPiece) {
                            existingPiece.remove(); // Remove the existing piece image
                        }
                        stopSquare.appendChild(draggedPiece);
                    })
                }
            })
            .catch(error => console.error('Error executing move:', error));

            if (draggedPiece) {
                draggedPiece.classList.remove('dragging'); // Remove dragging class
                console.log(move);
            }
        });
    });

    // Add event listeners to all pieces
    const pieces = document.querySelectorAll('.chess-board td img');

    pieces.forEach(piece => {
        // Event listener for when a piece starts being dragged
        piece.addEventListener('dragstart', (event) => {
            startSquareId = piece.parentElement.id;
            event.dataTransfer.setData('text/plain', ''); // Required for Firefox to enable dragging
            piece.classList.add('dragging'); // Add dragging class
        });

        // Event listener for when a piece finishes being dragged
        piece.addEventListener('dragend', () => {
            piece.classList.remove('dragging'); // Remove dragging class
        });
    });
});