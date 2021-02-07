function solution(newId) {
    // step 1
    newId = newId.toLowerCase();

    // step 2
    var tmp = '';
    for(var index = 0; index < newId.length; index++) {
        var ch = newId[index];

        if(('a' <= ch && ch <= 'z') || ('0' <= ch && ch <= '9') || ch == '-' || ch == '_' || ch == '.') {
            tmp += ch;
        }
    }
    newId = tmp;

    // step 3
    while(true) {
        if(newId.indexOf("..") < 0) break;

        newId = newId.replace("..", ".");
    }

    // step 4
    if(newId[0] == '.') {
        newId = newId.substr(1);
    }
    if(newId[newId.length - 1] == '.') {
        newId = newId.substr(0, newId.length - 1);
    }

    // step 5
    if(newId === "") {
        newId += 'a';
    }

    // step 6
    if(newId.length >= 16) {
        newId = newId.substr(0, 15);

        if(newId[newId.length - 1] == '.') {
            newId = newId.substr(0, newId.length - 1);
        }
    }

    // step 7
    if(newId.length <= 2) {
        var lastCh = newId[newId.length - 1];

        while(newId.length < 3) {
            newId += lastCh;
        }
    }

    return newId;
}