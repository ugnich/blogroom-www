<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>

<div class="navbar fixed-top navbar-light bg-dark">
    <div class="navbar-brand text-light">Комментарии для Телеграма</div>
</div>

<div class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbo-heading">Комментарии для Телеграма</h1>
        <p class="lead mt-3 mb-4">Подключи комментирование на своем Телеграм-канале и общайся с подписчиками</p>
        <p class="font-weight-bold">Отдельная лента комментариев для каждого поста</p>
        <p class="font-weight-bold">Простой бесплатный сервис без регистрации</p>
        <p class="font-weight-bold">От создателя легендарных микроблогов Juick</p>
        </p>
    </div>
</div>

<div class="container pt-3 pb-4">
    <h2 class="text-center mb-3">Как это работает?</h2>
    <p class="lead">1. Вводишь в нашу форму текст будущего поста (ну или любой другой текст)</p>
    <p class="lead">2. Жмешь "создать страницу для комментариев" и получаешь уникальную ссылку.</p>
    <p class="lead">3. Добавляешь эту ссылку в конец своего Телеграм-поста.</p>
    <p class="lead">4. Подписчики переходят по ссылке и оставляют кучу комментариев.</p>
</div>

<div class="container-fluid bg-light pt-4 pb-4">
    <div class="row">
        <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-2 col-xl-6 offset-xl-3">
            <h2 class="text-center mb-3">Попробуй:</h2>
            <div class="form-group">
                <textarea id="newposttext" class="form-control" rows="3" placeholder="Текст поста (будет вверху страницы с комментариями)"></textarea>
            </div>
            <div class="form-group text-center">
                <button class="btn btn-success" onclick="newTelePost(document.getElementById('newposttext'),this)">Создать страницу для комментариев</button>
            </div>
        </div>
    </div>
</div>

<div class="container text-center text-muted mt-5"><small>Сделано <a href="http://t.me/ugnich">@ugnich</a>, исходные коды доступны на <a href="https://github.com/ugnich/">GitHub</a></div>

<div id="modalLink" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Твоя ссылка</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Просто добавь в конце будущего поста:</p>
                <input id="teleurl" type="text" readonly class="form-control" onclick="this.setSelectionRange(0, this.value.length)"/>
            </div>
            <div class="modal-footer">
                <a id="telelink" href="#" class="btn btn-primary">Посмотреть страницу</a>
            </div>
        </div>
    </div>
</div>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
