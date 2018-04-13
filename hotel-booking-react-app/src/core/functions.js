/* *
  Functions
 */
const functions = {
    restUrlCall: async (url) => {
        const response = await fetch(url)
            .catch(e => console.log("Error when calling rest url", url));
        const json = await response.json();
        return json;
    }
}

export default functions;
