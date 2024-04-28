document.addEventListener('DOMContentLoaded', () => {
    const squares = document.querySelectorAll('.chess-board td');

    // Initial chess piece positions
    const startingPosition = [
        'br', 'bn', 'bb', 'bq', 'bk', 'bb', 'bn', 'br',  // Black back row
        'bp', 'bp', 'bp', 'bp', 'bp', 'bp', 'bp', 'bp',  // Black pawns
        '', '', '', '', '', '', '', '',                  // Empty rows
        '', '', '', '', '', '', '', '',                  // Empty rows
        '', '', '', '', '', '', '', '',                  // Empty rows
        '', '', '', '', '', '', '', '',                  // Empty rows
        'wp', 'wp', 'wp', 'wp', 'wp', 'wp', 'wp', 'wp',  // White pawns
        'wr', 'wn', 'wb', 'wq', 'wk', 'wb', 'wn', 'wr'   // White back row
    ];

    // Loop through squares and populate with pieces
    squares.forEach((square, index) => {
        const pieceCode = startingPosition[index];
        if (pieceCode !== '') {
            const pieceImg = document.createElement('img');
            pieceImg.src = `PiecePNG/${pieceCode}.png`;
            pieceImg.alt = pieceCode; // Using pieceCode as alt text
            pieceImg.style.width = '100%';
            pieceImg.style.height = '100%';
            square.appendChild(pieceImg);
        }
    });
});