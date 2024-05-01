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
                    const existingPiece = square.querySelector('.chess-board td img');
                    if (existingPiece) {
                        existingPiece.remove(); // Remove the existing piece image
                    }
                    square.appendChild(draggedPiece);


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