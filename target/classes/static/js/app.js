$( document ).ready(function() {
    console.log('laina');
    var _PREVIEW_URL;

    /* Show Select File dialog */
    /*$("#upload-dialog").click(function(){
        alert("The paragraph was clicked.");
    });*/
    document.querySelector("#upload-dialog").addEventListener('click', function() {
        document.querySelector("#image-file").click();
    });

    /* Selected File has changed */
    document.querySelector("#image-file").addEventListener('change', function() {
        // user selected file
        var file = this.files[0];

        // allowed MIME types
        var mime_types = [ 'image/jpeg', 'image/png' ];

        // validate MIME
        if(mime_types.indexOf(file.type) == -1) {
            alert('Error : Incorrect file type');
            return;
        }

        // validate file size
        if(file.size > 2*1024*1024) {
            alert('Error : Exceeded size 2MB');
            return;
        }

        // validation is successful

        // hide upload dialog button
        //document.querySelector("#upload-dialog").style.display = 'none';

        // object url
        _PREVIEW_URL = URL.createObjectURL(file);

        // set src of image and show
        document.querySelector("#preview-image").setAttribute('src', _PREVIEW_URL);
        document.querySelector("#preview-image").style.display = 'block';
    });
});

// will hold the local preview url
