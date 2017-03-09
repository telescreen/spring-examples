var app = new Vue({
    el: '#app',
    data: {
        csrfToken: $("meta[name='_csrf']").attr("content"),
        header: $("meta[name='_csrf_header']").attr("content"),
        accessKey: '',
        secretKey: '',
        exception: {
            error_code: '',
            message: '',
            request_id: '',
            host_id: ''
        }
    },
    methods: {
        Submit: function() {
            $.ajax({
                method: 'GET',
                url: '/api/listbuckets',
                data: {
                    accessKey: this.accessKey,
                    secretKey: this.secretKey
                },
                success: function(result) {
                    console.log(result);
                }
            });
        }
    }
});