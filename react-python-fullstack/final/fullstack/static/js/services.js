export const getGeneralAnalysis = () => {
    return fetch(`/general`, {
      method: 'GET'
    })
    .then( response => {
      if( response.ok ) {
        return response.json();
      } else {
        return Promise.reject('fetch-error');
      }
    });
  };

  export const inputNewData = (data) => {
    return fetch(`/input`, {
      method: 'POST',
      body: JSON.stringify({"data":data}),
      headers: new Headers({
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      })
    })
    .then( response => {
      if( response.ok ) {
        return response.json();
      } else {
        return Promise.reject('fetch-error');
      }
    });
  };

  export const predictPurchase = (data) => {
    return fetch(`/predict`, {
      method: 'POST',
      body: JSON.stringify({"data":data}),
      headers: new Headers({
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      })
    })
    .then( response => {
      if( response.ok ) {
        return response.json();
      } else {
        return Promise.reject('fetch-error');
      }
    });
  };