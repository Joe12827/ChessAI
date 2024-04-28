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
            const move = {from: startSquareId, to: square.id}
            if (draggedPiece) {
                // Move the dragged piece to the target square
                if (square.children.length === 0) { // Check if target square is empty
                    square.appendChild(draggedPiece); // Append piece to target square
                } else {
                    // Square is not empty, do not allow drop
                    alert('Invalid move! Square is occupied.');
                }
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