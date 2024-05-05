function runExternalScript() {
    alert("rest")
    // // Create a <script> element
    // var script = document.createElement('script');

    // // Set the source of the external JavaScript file
    // script.src = 'PageLoad.js';

    // // Append the <script> element to the <body> to load the external script
    // document.body.appendChild(script);
}

function startAIMatch() {
    // Create a <script> element
    var script = document.createElement('script');

    // Set the source of the external JavaScript file
    script.src = 'AIMatch.js';

    // Append the <script> element to the <body> to load the external script
    document.body.appendChild(script);
}

// Event listener for button click
document.getElementById('runPageLoad').onclick = function() {
    runExternalScript(); // Call the function to load and execute the external script
};

document.getElementById('AIMatch').onclick = function() {
    startAIMatch(); // Call the function to load and execute the external script
};
