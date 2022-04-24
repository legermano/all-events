<template>
  <div class="card h-100">
    <img class="card-img-top" src="https://picsum.photos/700/400" alt="">
    <div class="card-body">
      <h4 class="card-title">
        {{ event.nome }}
      </h4>
      <p class="card-text">
        <small class="text-muted">
          Data de inicio: {{ $filters.formatDate(event.dataInicio) }}
        </small>
        <br/>
        <small class="text-muted">
          Data de fim: {{ $filters.formatDate(event.dataFim) }}
        </small>
      </p>
      <p class="card-text">
        {{ event.descricao }}
      </p>
    </div>
    <div class="card-footer">
      <button  type="button" class="btn btn-secondary">
        Inscrever-se
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "EventCard",
  props: ['event']
}
</script>