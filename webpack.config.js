const path = require('path');
const ExtractTextPlugin = require("extract-text-webpack-plugin");
const CleanWebpackPlugin = require('clean-webpack-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin')

const src = '/src/main/resources/static';

console.log("path.resolve(__dirname " ,path.join(__dirname) ,src, '/dist');

let conf = {
    context: path.join(__dirname, src , '/js'),
    entry: {
        uploading: './main.js',
    },
    output: {
        path: path.join(__dirname, src + '/dist'),
        publicPath: "dist/",
        filename: '[name].build.js',
    },
    optimization: {
        noEmitOnErrors: true,
    },
    watchOptions: {
        poll: true
    },
    devServer: {
        contentBase: path.join(__dirname , src +'/dist'),
        compress: true,
        port: 8000,
        historyApiFallback: true,
        allowedHosts:[
            'localhost:8090'
        ]
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
        new VueLoaderPlugin(),
        new HtmlWebpackPlugin({
            title: 'Custom template',
            filename: 'index.html',
            template:  path.join(__dirname , src +'/index.html'),
        })
    ]
};

module.exports = ( env, options ) =>{
    let isProduction = options.mode === 'production';
    conf.devtool = isProduction ? false : 'eval-sourcemap';
    conf.watch = !isProduction;
    return conf;
};