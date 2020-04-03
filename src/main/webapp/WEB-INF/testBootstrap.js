(function () {

    var loadTheme = function (url) {
        console.log("loadTheme", url);

        var href = url + '/styles.css';
        var stylesheet = document.createElement('link');
        stylesheet.setAttribute('rel', 'stylesheet');
        stylesheet.setAttribute('type', 'text/css');
        stylesheet.setAttribute('href', href);
        document.getElementsByTagName('head')[0].appendChild(stylesheet);
    };

    loadTheme('styles.css');
    console.log('Bootstrap loaded');
})();