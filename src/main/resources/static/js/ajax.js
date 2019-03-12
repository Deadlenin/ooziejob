function getData(settings) {
    const url = settings.url || '';
    const method = settings.method || 'GET';
    const responseType = settings.responseType || 'json';
    const data = settings.data instanceof FormData ?  settings.data : settings.data ? JSON.stringify(settings.data) : null;
    const contentType = settings.contentType || null;

    return new Promise(function( resolve, reject ){
        let xhr = new XMLHttpRequest();
        xhr.responseType = responseType;
        xhr.open(method, encodeURI(url), true);
        contentType && xhr.setRequestHeader("Content-Type", contentType);
        xhr.onload = function(){
            if(this.status === 200){
                resolve(this.response);
            }
            else {
                reject(this);
            }
        };
        xhr.onerror = function( err ){
            reject(err);
        };
        xhr.send(data);
    });
}

export default getData;