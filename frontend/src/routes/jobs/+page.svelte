<script>
  import axios from "axios";
  import { page } from "$app/stores";
  import { onMount } from "svelte";

  const api_root = $page.url.origin;

  let currentPage; 
  let nrOfPages = 0; 
  let defaultPageSize = 30; 

  let earningsMin; 
  let jobType; 

  let jobs = [];
  let job = {
    description: null,
    earnings: null,
    jobType: null,
    companyId: null
  };

  onMount(() => {
    getJobs();
  });

  function getJobs() {

    let query = "?page=" + currentPage + "&size=" + defaultPageSize;

    if (earningsMin) {
      query += "&min=" + earningsMin;
    }
    if (jobType && jobType !== "ALL") {
      query += "&type=" + jobType;
    }

    var config = {
      method: "get",
      url: api_root + "/api/job" + query,
      headers: {},
    };

    axios(config)
      .then(function (response) {
        jobs = response.data.content;

        nrOfPages = response.data.totalPages; 
      })
      .catch(function (error) {
        alert("Could not get jobs");
        console.log(error);
      });
  }

  function createJob() {
    var config = {
      method: "post",
      url: api_root + "/api/job",
      headers: {
        "Content-Type": "application/json",
      },
      data: job,
    };

    axios(config)
      .then(function (response) {
        alert("Job created");
        getJobs();
      })
      .catch(function (error) {
        alert("Could not create Job");
        console.log(error);
      });
  }
</script>

<h1 class="mt-3">Create Job</h1>
<form class="mb-5">
  <div class="row mb-3">
    <div class="col">
      <label class="form-label" for="description">Description</label>
      <input
        bind:value={job.description}
        class="form-control"
        id="description"
        type="text"
      />
    </div>
  </div>
  <div class="row mb-3">
    <div class="col">
      <label class="form-label" for="type">Type</label>
      <select bind:value={job.jobType} class="form-select" id="type">
        <option value="OTHER">OTHER</option>
        <option value="TEST">TEST</option>
        <option value="IMPLEMENT">IMPLEMENT</option>
        <option value="REVIEW">REVIEW</option>
      </select>
    </div>
    <div class="col">
      <label class="form-label" for="earnings">Earnings</label>
      <input
        bind:value={job.earnings}
        class="form-control"
        id="earnings"
        type="number"
      />
    </div>
    <div class="col">
      <label class="form-label" for="companyid">Company ID</label>
      <input
        bind:value={job.companyId}
        class="form-control"
        id="companyid"
        type="text"
      />
    </div>
  </div>
  <button type="button" class="btn btn-primary" on:click={createJob}
    >Submit</button
  >
</form>

<h1>All Jobs</h1>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Description</th>
      <th scope="col">Type</th>
      <th scope="col">Earnings</th>
      <th scope="col">State</th>
      <th scope="col">CompanyId</th>
      <th scope="col">FreelancerId</th>
    </tr>
  </thead>
  <tbody>
    {#each jobs as job}
      <tr>
        <td>{job.description}</td>
        <td>{job.jobType}</td>
        <td>{job.earnings}</td>
        <td>{job.jobState}</td>
        <td>{job.companyId}</td>
        <td>{job.freelancerId}</td>
      </tr>
    {/each}
  </tbody>
</table>

<nav>
  <ul class="pagination">
    {#each Array(nrOfPages) as _, i}
      <li class="page-item">
        <a
          class="page-link"
          class:active={currentPage == i + 1}
          href={"/jobs?page=" + (i + 1)}
        >
          {i + 1}
        </a>
      </li>
    {/each}
  </ul>
</nav>