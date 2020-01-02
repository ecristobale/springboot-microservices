:: download consul.exe in https://www.consul.io/downloads.html
:: start Consul agent. Test it in:  http://localhost:8500/ui
consul agent -server -bootstrap-expect=1 -data-dir=consul-data -ui -bind=127.0.0.1