var socket;

function initWS(post_id) {
    if (window.WebSocket) {
        socket = new WebSocket("wss://blogroom.live/ws/"+post_id);
        socket.onmessage = function (event) {
            var msg=JSON.parse(event.data);
            if(msg.hasOwnProperty("status")) {
                document.getElementById("onlinecnt").textContent = "Online: " + msg.status.online;
            } else if(msg.hasOwnProperty("message")) {
                var comments=document.getElementById("comments");
                var card=createCard(msg.message.text);
                comments.appendChild(card);
                window.scrollTo(0,document.body.scrollHeight);
            }
        };
        socket.onopen = function (event) {
        };
        socket.onclose = function (event) {
        };
    }
}
    
function createCard(text) {
    var card=document.createElement("div");
    card.className="card mb-2";
    var cardBody=document.createElement("div");
    cardBody.className="card-body p-2";
    var cardText=document.createElement("p");
    cardText.className="card-text";
    cardText.textContent=text;
    cardBody.appendChild(cardText);
    card.appendChild(cardBody);
    return card;
}

function send(el) {
    if (!window.WebSocket) {
        return;
    }
    if (socket.readyState == WebSocket.OPEN) {
        var msg=el.value.trim();
        if(msg.length>0) {
            socket.send(el.value);
        }
        el.value="";
    } else {
        alert("The socket is not open.");
    }
}
    
function inputkeypress(e) {
    if(e.keyCode==13) {
        send(document.getElementById('newmessage'))
    }
    return true;
}

function openPost(e) {
    var post_id=e.getAttribute('data-post-id');
    window.location.href="https://blogroom.live/p/"+post_id;
}

function newPost(textarea,button) {
    var txt=textarea.value.trim();
    if(txt.length==0) {
        return;
    }
        
    button.setAttribute("disabled","disabled");
    
    $.ajax({
        method: "POST",
        url: "/api/newpost",
        data: {
            "text": txt,
            "hash": getCookie("hash")
        },
        dataType: "json",
        cache: false,
        success: function(data) {
            window.location.href="https://blogroom.live/p/"+data.post.post_id;
        },
        error: function() {
            button.removeAttribute('disabled');
        }
    });
}

window.getCookie = function(name) {
    match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
    if (match) return match[2];
}
