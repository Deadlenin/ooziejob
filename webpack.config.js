const path = require('path');
const ExtractTextPlugin = require("extract-text-webpack-plugin");
const CleanWebpackPlugin = require('clean-webpack-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin');

const src = 'src/main/resources/static';
let conf = {
    context: path.resolve(__dirname, src + '/js'),
    entry: {
        uploading: './main.js',
    },
    output: {
        path: path.resolve(__dirname, src + '/dist'),
        filename: '[name].build.js',
    },
    optimization: {
        noEmitOnErrors: true,
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /(node_modules)/,
                use: {
                    loader: 'babel-loader'
                }
            },
            {
                test: /\.css|.scss$/,
                use: ExtractTextPlugin.extract({
                    fallback: 'style-loader',
                    use: [ 'css-loader', 'sass-loader' ],
                })
            }, {
                test: /\.vue$/,
                loader: 'vue-loader'
            }
        ]
    },
    resolve: {
        extensions: ['.js', '.vue', '.json'],
    },
    plugins: [
        new CleanWebpackPlugin('dist', {}),
        new ExtractTextPlugin({ filename: '[name].build.css' }),
        new VueLoaderPlugin()
    ]
};

module.exports = ( env, options ) =>{
    let isProduction = options.mode === 'production';
    conf.devtool = isProduction ? false : 'eval-sourcemap';
    conf.watch = !isProduction;
    return conf;
};