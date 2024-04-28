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
            .then(() => {
                console.log('Move Executed Successfully');
                // Update HTML elements or display confirmation message
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